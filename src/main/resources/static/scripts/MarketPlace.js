import {rootURL} from "./Root.js"
import {getToken} from "./DummyLogin.js"
// import  "https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.js"

"use strict"

const assetDivList = document.getElementById("assetListContainer")

await getToken()
let token = localStorage.getItem("jwtToken")


class Asset {

    constructor(code, name, currentPrice) {
        this.name = name
        this.code = code
        this.currentPrice = currentPrice
    }
}

class PriceHistory {
    constructor(date, price, asset) {
        this.date = date
        this.price = price
        this.asset = asset
    }
}

//Declare functions
const tradeWithAsset = (asset) => {
    fetch(`${rootURL}trade`, {
        method: "POST",
        headers: {
            Accept: 'Application/json',
            "content-type": "application/json",
            'Access-Control-Allow-Origin': '*',
            'Access-Control-Allow-Methods': '*',
            'X-Content-Type-Options': '*',
            'authorization': token
        },
        body: JSON.stringify(asset)
    }).then(response => {
        if (response.ok) {
            return response.json()
        } else {
            console.log("there was an error with request" + response.error())
        }
    }).then(json => {
        assetDivList.innerText = json.code
    })
}

const createTradeButton = (asset) => {
    let tradeButton = document.createElement("button")
    tradeButton.className = "tradeButton"
    tradeButton.id = asset.code + "Button"
    tradeButton.innerHTML = "trade"
    tradeButton.addEventListener("click", function () {
        tradeWithAsset(new Asset(asset.code, asset.name, asset.currentValue))
        // localStorage.setItem("currentAsset", JSON.stringify(asset))
        // window.location.href = `${rootURL}/Test.html`;
    })
    return tradeButton
}

const createGraph = (priceHistory, dates, prices) => {
    new Chart(priceHistory, {
        type: "line",
        data: {
            labels: dates,
            datasets: [{
                legend: {display: false},
                pointBackgroundColor: "white",
                backgroundColor: "white",
                borderColor: "rgba(0,0,0,0.5)",
                data: prices,
                fill: false
            }]
        },
        options: {
            spanGaps: true,
            datasets: {
                line: {
                    pointRadius: 0 // disable for all `'line'` datasets
                }
            }
        }
    });
}
const createAssetDiv = (priceHistoriesOfAsset) => {

    const asset = priceHistoriesOfAsset[0].asset

    let assetDivElement = document.createElement("div");
    let assetCodeLabel = document.createElement("label");
    let assetNameLabel = document.createElement("label");
    let graphContainer = document.createElement("div")
    let priceHistory = document.createElement("canvas")
    let assetCurrentPriceLabel = document.createElement("label");
    graphContainer.appendChild(priceHistory)
    graphContainer.id = "graphContainer"
    assetDivElement.id = asset.code;
    assetCodeLabel.id = "code"
    assetNameLabel.id = "name"
    priceHistory.id = "priceHistory" + asset.code
    assetCurrentPriceLabel.id = "currentPrice"
    let dates = priceHistoriesOfAsset.map(ph => priceHistoriesOfAsset.indexOf(ph))
    let prices = priceHistoriesOfAsset.map(ph => ph.price)
    console.log(dates)
    console.log(prices)
    createGraph(priceHistory, dates, prices)

    assetCodeLabel.innerHTML = asset.code
    assetNameLabel.innerHTML = asset.name
    assetCurrentPriceLabel.innerHTML = asset.currentPrice
    assetDivElement.appendChild(assetCodeLabel)
    assetDivElement.appendChild(assetNameLabel)
    assetDivElement.appendChild(assetCurrentPriceLabel)
    assetDivElement.appendChild(graphContainer)
    assetDivElement.appendChild(createTradeButton(asset))
    assetDivElement.className = "asset"

    return assetDivElement
}

const setHTMLElementAssetList = (priceHistoriesByAssets) => {
    for (const priceHistoriesOfAsset of priceHistoriesByAssets) {
        assetDivList.appendChild(createAssetDiv(priceHistoriesOfAsset))
    }
}

function parseDate(localDateTimeString) {
    const dateTime = localDateTimeString.substring(0, 10) + " " + localDateTimeString.substring(11)
    const date = new Date(dateTime);
    return date
}

function jsonToPriceHistoriesByAssets(json) {
    const priceHistoriesByAssets = []
    for (const priceHistoriesOfAssetJson of json) {
        const priceHistoriesOfAsset = []
        for (const priceHistory of priceHistoriesOfAssetJson) {
            //console.log(priceHistory.dateTime)
            priceHistoriesOfAsset.push(new PriceHistory(parseDate(priceHistory.dateTime), priceHistory.price, priceHistory.asset))
        }
        priceHistoriesByAssets.push(priceHistoriesOfAsset)
    }
    return priceHistoriesByAssets
}

const getPriceHistoriesByAsset = (token) => {
    const dateNow = new Date()
    const date = new Date()
    console.log(date)
    date.setMonth(dateNow.getMonth() - 1)
    var dateString = date.toISOString().substring(0, 23);

    return fetch(`${rootURL}priceHistories`,
        {
            method: 'POST',
            headers: {
                Accept: 'Application/json',
                "content-type": "application/json",
                'Access-Control-Allow-Origin': '*',
                'Access-Control-Allow-Methods': '*',
                'X-Content-Type-Options': '*',
                'authorization': token
            },
            body: dateString

        }).then(promise => {
        if (promise.ok) {
            return promise.json()
        } else {
            console.log("Couldn't retrieve pricehistory from the server")
            return promise
        }
    }).then(json =>
        json
    )
}
// const getAssets = (token) => {
//     fetch(`${rootURL}marketplace`,
//         {
//             method: "GET",
//             headers: {
//                 Accept: 'Application/json',
//                 "content-type": "application/json",
//                 'Access-Control-Allow-Origin': '*',
//                 'Access-Control-Allow-Methods': '*',
//                 'X-Content-Type-Options': '*',
//                 'authorization': token
//             }
//         }).then(promise => {
//         if (promise.ok) {
//             return promise.json()
//         } else {
//             console.log("Something went wrong")
//         }
//     }).then(json => {
//             setAssetList(jsonToAssets(json))
//         }
//     ).catch(error =>
//         console.log(error)
//     )
// }

const jsonToAssets = (jsonAssets) => {
    let assets = new Array();
    for (const jsonAsset of jsonAssets) {
        const asset = new Asset();
        asset.code = jsonAsset.code
        asset.name = jsonAsset.name
        asset.currentPrice = jsonAsset.currentValue
        assets.push(asset)
    }
    assets.sort()
    return assets
}

//getAssets(token)
const json = await getPriceHistoriesByAsset(token)
const priceHistoriesByAssets = jsonToPriceHistoriesByAssets(json)
//const assetList = []
// for(const priceHistoriesOfAsset of priceHistoriesByAssets){
//     assetList.push(priceHistoriesOfAsset[0].asset)
// }
//console.log("priceHistoriesByAssets: "+ priceHistoriesByAssets)
setHTMLElementAssetList(priceHistoriesByAssets)



