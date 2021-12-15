// Created by Deek
// Creation date 12/11/2021

package com.example.project_bigbangk.service.priceHistoryUpdate;

import com.example.project_bigbangk.model.Asset;
import com.example.project_bigbangk.model.AssetCode_Name;
import com.example.project_bigbangk.model.PriceHistory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.*;


public class CoinMarketCapNegociator implements ICryptoApiNegotiatorService {

    private final Logger logger = LoggerFactory.getLogger(CoinMarketCapNegociator.class);
    private final static String API_KEY = "9a38f7d6-3288-491a-8a0d-0338079527bc";
    private final static String ASSET_DATA_URL = "https://pro-api.coinmarketcap.com/v1/cryptocurrency/quotes/latest";
    private final static String SERVER_INFO_URL = "https://pro-api.coinmarketcap.com/v1/key/info";
    private final static String ALLASSETS = "https://pro-api.coinmarketcap.com/v1/cryptocurrency/listings/latest";
    private final static int[] ASSET_IDS = new int[]{1, 1027, 1839, 825, 5426, 3408, 2010, 52, 6636, 74, 4172, 5805, 5994, 4687, 3635, 3890, 3717, 2, 7083, 1027, 4943,};


    CloseableHttpClient client;

    public CoinMarketCapNegociator() {
        super();
        logger.info("New CoinMarketCapNegociator");
        client = HttpClients.createDefault();
    }

    @Override
    public boolean isAvailable() {
        String result = null;
        List<NameValuePair> parameters = new ArrayList<>();
        parameters.add(new BasicNameValuePair("id", getCoinIdsAsString()));
        parameters.add(new BasicNameValuePair("aux", "status"));
        try {
            URIBuilder query = makeAPIQuery(SERVER_INFO_URL, parameters);
            HttpGet request = makeApiRequest(query);
            return getStatusCode(request) == 200;
        } catch (IOException e) {
            System.out.println("Error: cannont access content - " + e.toString());
        } catch (URISyntaxException e) {
            System.out.println("Error: Invalid URL " + e.toString());
        }
        return true;
    }

    @Override
    public List<PriceHistory> getPriceHistory() {
        String jsonResult = null;
        List<NameValuePair> parameters = new ArrayList<>();
        parameters.add(new BasicNameValuePair("id", getCoinIdsAsString()));
        parameters.add(new BasicNameValuePair("convert", "EUR"));
        try {
            URIBuilder query = makeAPIQuery(ASSET_DATA_URL, parameters);
            HttpGet request = makeApiRequest(query);
            jsonResult = makeAPiCall(request);
        } catch (IOException e) {
            System.out.println("Error: cannont access content - " + e.toString());
        } catch (URISyntaxException e) {
            System.out.println("Error: Invalid URL " + e.toString());
        }
        List<PriceHistory> priceHistories = convertJSonToPriceHistory(jsonResult);
        return priceHistories;
    }

    /**
     *
     * @return a list of currentPrices for the first 20 most populair assets
     */
    public List<PriceHistory> getRangeOfCoins() {
        String jsonResult = null;
        List<NameValuePair> parameters = new ArrayList<>();
        parameters.add(new BasicNameValuePair("limit", "20"));
        parameters.add(new BasicNameValuePair("convert", "EUR"));
        try {
            URIBuilder query = makeAPIQuery(ALLASSETS, parameters);
            HttpGet request = makeApiRequest(query);
            jsonResult = makeAPiCall(request);
        } catch (IOException e) {
            System.out.println("Error: cannont access content - " + e.toString());
        } catch (URISyntaxException e) {
            System.out.println("Error: Invalid URL " + e.toString());
        }
        return convertJSonToPriceHistory(jsonResult);
    }

    private List<PriceHistory> convertJSonToPriceHistory(String result) {
        ObjectNode node = null;
        try {
            node = new ObjectMapper().readValue(result, ObjectNode.class);
        } catch (JsonProcessingException e) {
            logger.info(e.getMessage());
        }

        List<PriceHistory> priceHistorys = new ArrayList<>();
        if (node.has("data")) {
            for (JsonNode json : node.findValues("data")) {
                for (JsonNode coin : json) {
                    double price = Double.parseDouble(coin.get("quote").get("EUR").get("price").toString());
                    AssetCode_Name assetCodeName = AssetCode_Name.valueOf(coin.get("symbol").textValue());
                    JsonNode jsonName = coin.get("name");
                    PriceHistory priceHistory = new PriceHistory(LocalDateTime.now(), price, new Asset(assetCodeName, price));
                    priceHistorys.add(priceHistory);
                }
            }
        }
        return priceHistorys;
    }

    private String getCoinIdsAsString() {
        StringBuilder stringBuilder = new StringBuilder();
        int i = 0;
        for (int assetCode : ASSET_IDS) {
            stringBuilder.append(assetCode);
            i++;
            if (i < ASSET_IDS.length) {
                stringBuilder.append(",");
            }
        }
        return stringBuilder.toString();
    }

    private URIBuilder makeAPIQuery(String uri, List<NameValuePair> parameters)
            throws URISyntaxException, IOException {
        String response_content = "";
        URIBuilder query = new URIBuilder(uri);
        query.addParameters(parameters);
        return query;
    }

    private HttpGet makeApiRequest(URIBuilder query) throws URISyntaxException, IOException {
        HttpGet request = new HttpGet(query.build());
        request.setHeader(HttpHeaders.ACCEPT, "application/json");
        request.addHeader("X-CMC_PRO_API_KEY", API_KEY);
        logger.info(request.toString());
        return request;
    }

    private int getStatusCode(HttpGet request) throws URISyntaxException, IOException {
        CloseableHttpResponse response = client.execute(request);
        int responseStatusCode;
        try {
            responseStatusCode = response.getStatusLine().getStatusCode();
        } finally {
            response.close();
        }
        return responseStatusCode;
    }

    private String makeAPiCall(HttpGet request) throws URISyntaxException, IOException {
        CloseableHttpResponse response = client.execute(request);
        String response_content = "";
        try {
            HttpEntity entity = response.getEntity();
            response_content = EntityUtils.toString(entity);
            EntityUtils.consume(entity);
        } finally {
            response.close();
        }
        return response_content;
    }


}