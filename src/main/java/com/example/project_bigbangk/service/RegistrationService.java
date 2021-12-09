package com.example.project_bigbangk.service;
/**
@Author Bigbangk
*/

import com.example.project_bigbangk.model.*;
import com.example.project_bigbangk.model.DTO.RegistrationDTO;
import com.example.project_bigbangk.repository.RootRepository;
import com.example.project_bigbangk.service.Security.HashService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Takes the RegistrationDTO, checks the data within and if correct creates client(with hashed PW), wallet(with generated IBAN) and address objects and sends them to rootrepo for storage.
 *uses HashService and IbanGeneratorService
 *
 */
@Service
public class RegistrationService {

    private RootRepository rootRepository;
    //source:https://howtodoinjava.com/java/regex/java-regex-validate-email-address/
    private static final String EMAIL_REGEX = "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
    //min 8 tekens, geen spaties of tabs
    private static final String PASSWORD_REGEX = "^(?=\\S+$).{8,}$";
    //alleen cijfers precies 9
    private static final String BSN_REGEX = "^[0-9]{9}$";
    //4 cijfers twee letters, spatie er tussen mag
    private static final String POSTAL_REGEX = "^[1-9]{1}[0-9]{3} ?[A-Z]{2}$";
    //precies 3 letters
    private static final String COUNTRY_REGEX = "^[a-zA-Z]{3}$";
    private final int AGE_LIMIT = 18;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private LocalDate convertedDateOfBirth;
    private HashService hashService;
    private IbanGeneratorService ibanGenerator;

    public RegistrationService(HashService hashService, IbanGeneratorService ibanGenerator, RootRepository rootRepository) {
        this.hashService = hashService;
        this.ibanGenerator = ibanGenerator;
        this.rootRepository = rootRepository;
    }

    public String registerClient(RegistrationDTO registrationDTO){
        Client existingClient = rootRepository.findClientByEmail(registrationDTO.getEmail());
        if(existingClient != null){
            return "Duplicate Email";
        }
        if(checkRegistrationInput(registrationDTO)){
            System.out.println(registrationDTO.getFirstName() + " " + registrationDTO.getLastName());

            Date dateOfBirth = java.sql.Date.valueOf(convertedDateOfBirth);
            Wallet wallet = new Wallet(ibanGenerator.getIban(),10000);
            Address address = new Address(registrationDTO.getPostalCode(),registrationDTO.getStreet(), registrationDTO.getNumber(), registrationDTO.getCity(),
                    registrationDTO.getCountry());
            Client client = new Client(registrationDTO.getFirstName(), registrationDTO.getInsertion(), registrationDTO.getLastName(), registrationDTO.getEmail(),
                    registrationDTO.getBsn(), dateOfBirth, hashService.hash(registrationDTO.getPassword()), address, wallet);

            rootRepository.createNewlyRegisteredClient(client);

            return "Registration Successful";
        }else{
            System.out.println("error.");
            return "Incorrect Input";
        }
    }

    /**
     * checks registrationDTO input.
     * failstates are empty strings(except insertion), not matching specific REGEX for postalcode, bsn, email and password,
     * and also if the difference between dateofbirth and now isn't under the
     * @param registrationDTO
     * @return
     */
    public boolean checkRegistrationInput(RegistrationDTO registrationDTO){
        if (checkForEmptyStrings(registrationDTO)) return false;
        if (!matchesRegex(registrationDTO.getEmail(), EMAIL_REGEX)){
            System.out.println("email");
            return false;}
        if (!matchesRegex(registrationDTO.getPassword(), PASSWORD_REGEX)){
            System.out.println("password");
            return false;}
        if (checkUnderAgeLimit(registrationDTO)) return false;
        if(!matchesRegex(registrationDTO.getBsn(), BSN_REGEX)){
            System.out.println("bsn");
            return false;}
        if(!matchesRegex(registrationDTO.getPostalCode(), POSTAL_REGEX)){
            System.out.println("post");
            return false;}
        if(registrationDTO.getNumber() < 1){return false;}
        if(!matchesRegex(registrationDTO.getCountry(), COUNTRY_REGEX)){
            System.out.println("country");
            return false;
        }
        return true;
    }

    private boolean checkUnderAgeLimit(RegistrationDTO registrationDTO) {
        convertedDateOfBirth = LocalDate.parse(registrationDTO.getDateOfBirth(), formatter);
        if(Period.between(convertedDateOfBirth, LocalDate.now()).getYears() < AGE_LIMIT){
            System.out.println(LocalDate.now());
            System.out.println("age");
            return true;
        }
        return false;
    }

    //voegt alle input die strings zijn aan een list toe en loopt er doorheen om te kijken of ze niet leeg zijn. (behalve insertion die mag leeg zijn)
    private boolean checkForEmptyStrings(RegistrationDTO registrationDTO) {
        List<String> inputNotNullList = Arrays.asList(registrationDTO.getFirstName(), registrationDTO.getLastName(),
                registrationDTO.getBsn(), registrationDTO.getDateOfBirth(), registrationDTO.getEmail(), registrationDTO.getPassword(), registrationDTO.getStreet(),
                registrationDTO.getPostalCode(), registrationDTO.getCountry(), registrationDTO.getCity());

        for (String s : inputNotNullList) {
            if(s == null || s.isEmpty()){
                System.out.println("list");
                return true;
            }}
        return false;
    }

    //controleerd string tegen regular expression
    public boolean matchesRegex(String input, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }

}
