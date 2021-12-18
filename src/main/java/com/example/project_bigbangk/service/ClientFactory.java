// Created by Deek
// Creation date 12/15/2021

package com.example.project_bigbangk.service;

import com.example.project_bigbangk.model.Address;
import com.example.project_bigbangk.model.Client;
import com.example.project_bigbangk.model.DTO.RegistrationDTO;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.h2.util.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.Normalizer;
import java.time.LocalDate;
import java.util.*;

@Service
public class ClientFactory {

    private final Logger logger = LoggerFactory.getLogger(ClientFactory.class);
    private Map<String, String> lastNames;
    private List<String> firstNames;
    private Set<String> cities;
    private List<String> streetNames;
    private RegistrationService registrationService;

    private List<String> providers = Arrays.asList("Chello", "xs4All", "Yahoo", "Gmail", "Wanadoo", "T-Mobile", "Samsung", "work", "Pixelpool");
    private List<String> EMAIL_EXTENSIONS = Arrays.asList("nl", "com", "de", "be", "biz", "fr", "vs", "org", "uk");
    private final String[] COUNTRIES = {"NLD", "BEL", "GER", "USA", "GBR", "FRA", "POL", "CHE"};
    private final Random RAND = new Random();
    private IbanGeneratorService ibanGeneratorService;

    public ClientFactory(IbanGeneratorService ibanGeneratorService, RegistrationService registrationService) {
        super();
        logger.info("New ClientFactory");
        firstNames = new ArrayList<>();
        lastNames = new HashMap<>();
        this.ibanGeneratorService = ibanGeneratorService;
        this.registrationService = registrationService;
    }


    public void createClients(int numberOfClients) {
        logger.info(String.format("Database seeding started for %s clients", numberOfClients));
        initializeNameArrays();
        initializeAdressArrays();
        List<Client> clients = new ArrayList<>();
        for (int i = 0; i < numberOfClients; i++) {
            Client client = createClient();
            client.setAddress(createAdress());
            clients.add(client);
        }
        int i = 0;
        int taskNumber = 4;
        for (int j = 0; j < taskNumber; j++) {
            try {
                final int x = j;
                new Task() {
                    int jInternal = x;

                    @Override
                    public void call() throws Exception {
                        for (int k = (clients.size() / taskNumber) * jInternal; k < (clients.size() / taskNumber) * (jInternal + 1); k++) {

                            RegistrationDTO clientDTO = new RegistrationDTO(clients.get(k).getEmail(),
                                    "password",
                                    clients.get(k).getFirstName(),
                                    clients.get(k).getInsertion(),
                                    clients.get(k).getLastName(),
                                    clients.get(k).getBsn(),
                                    createBirthDay(),
                                    clients.get(k).getAddress().getPostalCode(),
                                    clients.get(k).getAddress().getStreet(),
                                    clients.get(k).getAddress().getNumber(),
                                    clients.get(k).getAddress().getCity(),
                                    clients.get(k).getAddress().getCountry());
                            registrationService.registerClient(clientDTO);
                        }
                    }
                }.call();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        logger.info("Database seeding end");
    }

    private void initializeAdressArrays() {
        this.cities = initializePlaatsnamen();
        this.streetNames = initializeStreetNames();
    }

    private List<String> initializeStreetNames() {
        File streetNames = new File("src/main/resources/straatnamen.txt");
        List<String> tempStreetNames = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(streetNames);
            while (scanner.hasNext()) {
                String streetname = scanner.nextLine();
                streetname = streetname.trim();
                tempStreetNames.add(streetname);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return tempStreetNames;
    }


    private Address createAdress() {
        String postalCode = createPostalCode();
        String street = streetNames.get(RAND.nextInt(streetNames.size()));
        int number = RAND.nextInt(300) + 1;
        String country = COUNTRIES[RAND.nextInt(COUNTRIES.length)];
        String city = (String) cities.toArray()[RAND.nextInt(cities.size())];
        return new Address(postalCode, street, number, city, country);
    }

    private String createPostalCode() {
        return String.format("%s%s%s", (RAND.nextInt(9000) + 1000),
                Character.toUpperCase((char) (RAND.nextInt(26) + 65)),
                Character.toUpperCase((char) (RAND.nextInt(26) + 65)));
    }

    private Client createClient() {
        Client client = new Client();
        client.setFirstName(firstNames.get(RAND.nextInt(firstNames.size())));
        client.setLastName((String) (lastNames.keySet().toArray()[RAND.nextInt(lastNames.size())]));
        client.setInsertion(lastNames.get(client.getLastName()));
        client.setDateOfBirth(LocalDate.parse(createBirthDay()));
        client.setEmail(createEmail(client.getFirstName()));
        client.setBsn(creatBsn());
        client.setPassWord("password");
        return client;
    }

    private String createBirthDay() {
        int maand = RAND.nextInt(12) + 1;
        int dag = RAND.nextInt(maand == 2 ? 28 : 30) + 1;
        return String.format("%s-%s-%s", RAND.nextInt(70) + 1932, makeDoubleNumber(maand), makeDoubleNumber(dag));
    }

    private String makeDoubleNumber(int number) {
        if ((String.valueOf(number).length() == 1)) {
            return String.format("0%s", number);
        }
        return String.valueOf(number);
    }

    private String creatBsn() {
        String firsthalf = String.valueOf(RAND.nextInt(9000) + 1000);
        String reversed = "";
        for (int i = firsthalf.length() - 1; i >= 0; i--) {
            reversed += firsthalf.charAt(i);
        }
        return String.format("%s%s%s", firsthalf, reversed, "0");
    }

    private String createEmail(String firstName) {
        String email = String.format("%s@%s.%s", firstName,
                providers.get(RAND.nextInt(providers.size())),
                EMAIL_EXTENSIONS.get(RAND.nextInt(EMAIL_EXTENSIONS.size()))
        );
        return email;
    }

    private void initializeNameArrays() {
        lastNames = initializeLastNames();
        firstNames = initializeFirstNames();

    }

    public Map<String, String> initializeLastNames() {
        File lastNamesFile = new File("src/main/resources/LastNames.json");
        Map<String, String> tempLastNames = new HashMap<>();
        JsonNode lastNamesNode;
        ObjectMapper mapper = new ObjectMapper();
        try {
            lastNamesNode = mapper.readTree(lastNamesFile);
            lastNamesNode = lastNamesNode.findValue("record");
            for (JsonNode lastNameJN : lastNamesNode) {
                String lastName =lastNameJN.get("naam").asText();
                String nameNorm = Normalizer.normalize(lastName, Normalizer.Form.NFD);
                if (!lastName.equals(nameNorm)) {
                    nameNorm = nameNorm.replaceAll("[^\\p{ASCII}]", "");
                }
                tempLastNames.put(nameNorm, lastNameJN.get("prefix").asText());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tempLastNames;
    }

    private Set<String> initializePlaatsnamen() {
        File cityFile = new File("src/main/resources/metatopos-places.json");
        Set<String> tempCities = new HashSet<>();
        JsonNode cityNodes;
        ObjectMapper mapper = new ObjectMapper();
        try {
            cityNodes = mapper.readTree(cityFile);
            cityNodes = cityNodes.findValue("places");
            for (JsonNode cityname : cityNodes) {
                tempCities.add(cityname.get("municipality").asText());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tempCities;
    }

    public List<String> initializeFirstNames() {
        File lastNamesFile = new File("src/main/resources/FirstNames.json");
        List<String> tempListFirstNames = new ArrayList<>();
        JsonNode lastNamesNode;
        ObjectMapper mapper = new ObjectMapper();
        try {
            lastNamesNode = mapper.readTree(lastNamesFile);
            JsonNode lastNamesNodes = lastNamesNode.findValue("database");
            lastNamesNodes = lastNamesNodes.findValue("record");
            for (JsonNode firstNameJN : lastNamesNodes) {
                String firstName = firstNameJN.get("voornaam").asText();
                String nameNorm = Normalizer.normalize(firstName, Normalizer.Form.NFD);
                if (!firstName.equals(nameNorm)) {
                    nameNorm = nameNorm.replaceAll("[^\\p{ASCII}]", "");

                }
                tempListFirstNames.add(nameNorm);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tempListFirstNames;
    }

}