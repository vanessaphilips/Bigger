// Created by Deek
// Creation date 12/15/2021

package com.example.project_bigbangk.service;

import com.example.project_bigbangk.model.Address;
import com.example.project_bigbangk.model.Client;
import com.example.project_bigbangk.model.DTO.RegistrationDTO;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
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

    private List<String> providers = Arrays.asList("Chello", "xs4All", "Yahoo", "Gmail");
    private List<String> EMAIL_EXTENSIONS = Arrays.asList("nl", "com", "de", "be", "biz");
    private final String[] COUNTRIES = {"NLD", "BE", "GER", "VS", "UK", "FR"};
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
        initializeNameArrays();
        initializeAdressArrays();
        List<Client> clients = new ArrayList<>();
        for (int i = 0; i < numberOfClients; i++) {
            //public Client(String email, String firstName, String insertion, String lastName, LocalDate dateOfBirth,
            //                  String bsn, String passWord, Address address, Wallet wallet) {
            Client client = createClient();
            client.setAddress(createAdress());
            clients.add(client);
        }
        for (Client client : clients) {
            RegistrationDTO clientDTO = new RegistrationDTO(client.getEmail(),
                    "password",
                    client.getFirstName(),
                    client.getInsertion(),
                    client.getLastName(),
                    client.getBsn(),
                    createBirthDay(),
            client.getAddress().getPostalCode(),
            client.getAddress().getStreet(),
            client.getAddress().getNumber(),
            client.getAddress().getCity(),
            client.getAddress().getCountry());
            registrationService.registerClient(clientDTO);
        }

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
        int number = RAND.nextInt(300);
        String country = COUNTRIES[RAND.nextInt(COUNTRIES.length)];
        String city = (String) cities.toArray()[RAND.nextInt(cities.size())];
        return new Address(postalCode, street, number, city, country);
    }

    private String createPostalCode() {
        return String.format("%s%s%s", (RAND.nextInt(10000)),
                Character.toUpperCase((char) (RAND.nextInt(15)+65)),
                Character.toUpperCase((char) (RAND.nextInt(15)+65)));
    }

    private Client createClient() {
        Client client = new Client();
        client.setFirstName(firstNames.get(RAND.nextInt(firstNames.size())));
        client.setLastName((String)(lastNames.keySet().toArray()[RAND.nextInt(lastNames.size())]));
        client.setInsertion(lastNames.get(client.getLastName()));
        client.setDateOfBirth(LocalDate.parse(createBirthDay()));
        client.setEmail(createEmail(client.getFirstName()));
        client.setBsn(creatBsn());
        client.setPassWord("password");
        return client;
    }

    private String createBirthDay() {
        int maand = RAND.nextInt(12)+1;
        int dag = RAND.nextInt(maand==2?28:30)+1;
        return String.format("%s-%s-%s", RAND.nextInt(70) + 1932, makeDoubleNumber(maand), makeDoubleNumber(dag));

    }
    private String makeDoubleNumber(int number){
        if ((String.valueOf(number).length()==1)){
          return String.format("0%s", number);
        }
        return String.valueOf(number);
    }

    private String creatBsn() {
        String firsthalf = String.valueOf(RAND.nextInt(10000));
        String reversed = "";
        for (int i = firsthalf.length() - 1; i >= 0; i--) {
            reversed += firsthalf.charAt(i);
        }
        return String.format("%s%s%s", firsthalf, reversed, "0");
    }

    private String createEmail(String firstName) {
        String email = String.format("%s@%s.nl", firstName,
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
                tempLastNames.put(lastNameJN.get("naam").asText(), lastNameJN.get("prefix").asText());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tempLastNames;
    }

    private Set<String> initializePlaatsnamen() {
        File cityFile = new File("src/main/resources/metatopos-places.json");
        Set<String> tempCities = new HashSet<>();
        JsonNode cityNode;
        ObjectMapper mapper = new ObjectMapper();
        try {
            cityNode = mapper.readTree(cityFile);
            cityNode = cityNode.findValue("places");
            for (JsonNode lastNameJN : cityNode) {
                tempCities.add(lastNameJN.get("municipality").asText());
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
            for (JsonNode lastNameJN : lastNamesNodes) {
                tempListFirstNames.add(lastNameJN.get("voornaam").asText());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tempListFirstNames;
    }

}