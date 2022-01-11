// import {rootURL} from "./URLs.js"
// import {getToken} from "./DummyLogin.js"
// import  "https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.js"

"use strict"

const assetDivList = document.getElementById("assetListContainer")
const MAX_DAYS_BACK = 60

await getToken()
let token = localStorage.getItem("jwtToken")
console.log("marketplace" + token + Date.now())
let daysBackInputField = document.getElementById("daysBack");
let daysBack = 30
daysBackInputField.innerText = daysBack;

function updatePriceHistoryGraphs(priceHistoriesByAssets) {
    for (const priceHistoriesOfAsset of priceHistoriesByAssets) {
        const asset = priceHistoriesOfAsset[0].asset
        let priceHistoryContainer = document.getElementById("graphContainer" + asset.code)
        while (priceHistoryContainer.firstChild) {
            priceHistoryContainer.removeChild(priceHistoryContainer.firstChild);
        }
        let priceHistoryGraph = createGraph(priceHistoriesOfAsset)
        priceHistoryGraph.id = "priceHistory" + asset.code
        priceHistoryGraph.className = "priceHistoryGraph"
        priceHistoryContainer.appendChild(priceHistoryGraph)
    }
}

daysBackInputField.addEventListener("focusout", async (event) => {
    console.log("field is veranderd")
    daysBack = daysBackInputField.value
    const jsonPriceHistories = await getPriceHistoriesByAsset(token)
    if (jsonPriceHistories !== undefined) {
        updatePriceHistoryGraphs(jsonToPriceHistoriesByAssets(jsonPriceHistories));
    }

})


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

function creatAssetCodeLabel(asset) {
    let assetCodeLabel = document.createElement("label");
    assetCodeLabel.id = "code"
    assetCodeLabel.innerHTML = asset.code
    return assetCodeLabel
}

function creatAssetNameLabel(asset) {
    let assetNameLabel = document.createElement("label");
    assetNameLabel.id = "name"
    assetNameLabel.innerHTML = asset.name
    return assetNameLabel
}

function creatCurrentPriceLabel(asset) {
    let assetcurrentPriceLabel = document.createElement("label");
    assetcurrentPriceLabel.id = "price"
    assetcurrentPriceLabel.innerHTML = asset.currentPrice
    return assetcurrentPriceLabel
}

function creatGraphContainer(asset, priceHistoriesOfAsset) {
    let graphContainer = document.createElement("div");

    let priceHistoryGraph = createGraph(priceHistoriesOfAsset)
    priceHistoryGraph.id = "priceHistory" + asset.code
    priceHistoryGraph.className = "priceHistoryGraph"
    graphContainer.appendChild(priceHistoryGraph)
    graphContainer.id = "graphContainer" + asset.code
    graphContainer.className = "graphContainer"
    return graphContainer
}

const createDivPerAsset = (priceHistoriesOfAsset) => {
    let assetDivElement = document.createElement("div");
    if (priceHistoriesOfAsset.length > 0) {
        const asset = priceHistoriesOfAsset[0].asset
        assetDivElement.id = asset.code;
        assetDivElement.className = "asset"
        assetDivElement.appendChild(creatAssetCodeLabel(asset))
        assetDivElement.appendChild(creatAssetNameLabel(asset))
        assetDivElement.appendChild(creatCurrentPriceLabel(asset))
        assetDivElement.appendChild(creatGraphContainer(asset, priceHistoriesOfAsset))
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
    return new Date(dateTime)
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

function createDateInPast() {
    const date = new Date(new Date().valueOf() - daysBack * 86400000)
    console.log(date)
    return date.toISOString().substring(0, 23);
}


const getPriceHistoriesByAsset = (token) => {
    console.log("getPriceHistroyToken"+token+ Date.now())
    return fetch(`${rootURL}priceHistories`,
        {
            method: 'POST',
            headers: acceptHeadersWithToken(token),
            body: createDateInPast()
        }).then(promise => {
        console.log(promise)
        if (promise.ok) {
            return promise.json()
        } else {
            console.log("Couldn't retrieve pricehistory from the server")
        }
    }).then(json => json)
}


const jsonPriceHistories = await getPriceHistoriesByAsset(token)
if (jsonPriceHistories !== undefined) {
    const priceHistoriesByAssets = jsonToPriceHistoriesByAssets(jsonPriceHistories)
    setHtmlElementAssetList(priceHistoriesByAssets)
}




