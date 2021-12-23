// import {rootURL} from "./URLs.js"
// import {getToken} from "./DummyLogin.js"
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

const createTradeButton = (asset) => {
    let tradeButton = document.createElement("label")
    tradeButton.className = "tradeButton"
    tradeButton.id = asset.code + "Button"
    tradeButton.innerHTML = "trade"
    tradeButton.addEventListener("click", function () {
        localStorage.setItem("currentAsset", JSON.stringify(asset))
        window.location.href = tradeButtonLink;
    })
    return tradeButton
}

function setInnerHtml(assetCodeLabel, assetNameLabel, assetCurrentPriceLabel, asset) {
    assetCodeLabel.innerHTML = asset.code
    assetNameLabel.innerHTML = asset.name
    assetCurrentPriceLabel.innerHTML = asset.currentPrice
}

const createDivPerAsset = (priceHistoriesOfAsset) => {
    let assetDivElement = document.createElement("div");
    if (priceHistoriesOfAsset.length > 0) {
        const asset = priceHistoriesOfAsset[0].asset

        let assetCodeLabel = document.createElement("label");
        let assetNameLabel = document.createElement("label");
        let graphContainer = document.createElement("div")
        let assetCurrentPriceLabel = document.createElement("label");
        let priceHistoryGraph = createGraph(priceHistoriesOfAsset)

        graphContainer.id = "graphContainer"
        assetDivElement.id = asset.code;
        assetCodeLabel.id = "code"
        assetNameLabel.id = "name"
        assetCurrentPriceLabel.id = "currentPrice"
        priceHistoryGraph.id = "priceHistory" + asset.code
        priceHistoryGraph.className = "priceHistoryGraph"
        assetDivElement.className = "asset"
        graphContainer.appendChild(priceHistoryGraph)
        setInnerHtml(assetCodeLabel, assetNameLabel, assetCurrentPriceLabel, asset)

        assetDivElement.appendChild(assetCodeLabel)
        assetDivElement.appendChild(assetNameLabel)
        assetDivElement.appendChild(assetCurrentPriceLabel)
        assetDivElement.appendChild(graphContainer)
        assetDivElement.appendChild(createTradeButton(asset))

    }
    return assetDivElement
}

const setHtmlElementAssetList = (priceHistoriesByAssets) => {
    for (const priceHistoriesOfAsset of priceHistoriesByAssets) {
        assetDivList.appendChild(createDivPerAsset(priceHistoriesOfAsset))
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


const json = await getPriceHistoriesByAsset(token)
const priceHistoriesByAssets = jsonToPriceHistoriesByAssets(json)
setHtmlElementAssetList(priceHistoriesByAssets)



