"use strict"
//Declare constants

const MARKETPLACE_ROOT_CONTAINER = document.getElementById("assetListContainer")
const TRADEBUTTON_CLASS = "tradeButton"

const ASSETCODELABEL_ID = "code"
const ASSETNAMELABEL_ID = "name"
const ASSETCURRENTPRICELABEL_ID = "price"

const PRICEHISTORYGRAPH_CLASS = "priceHistoryGraph"
const PRICEHISTORYGRAPH_ID_PREFIX = "priceHistory"

const GRAPHCONTAINER_CLASS = "graphContainer"
const GRAPHCONTAINER_ID_PREFIX = "graphContainer"

const ASSETCONTAINER_CLASS = "assetContainer"
const SELECTED_ASSETCONTAINER_CLASS = "selectedAsset"
const DAYSBACKINPUTFIELD_ID = "daysBack"
const ROUNDING_DIGITS = 2

const GRAPHWIDTH = 200;
const GRAPHHEIGHT = 100;
let updateInterval = 300000;
let lastUpdate = Date.now();
await getToken()
let token = localStorage.getItem(JWT_KEY)
let daysBackInputField = document.getElementById(DAYSBACKINPUTFIELD_ID);
let daysBack = 30

let priceHistoriesByAssets = [[]]

//Declare functions
async function initializePage() {
    daysBackInputField.value = daysBack;
    console.log("pageUpdated on " + new Date(Date.now()).toLocaleTimeString())
    const json = await getPriceHistoriesByAsset(token)
    updateInterval = Number.parseFloat(json.updateInterval);
    const jsonPriceHistories = JSON.parse(json.priceHistory);
    if (jsonPriceHistories !== undefined) {
        saveAsPriceHistories(jsonPriceHistories)
        lastUpdate = priceHistoriesByAssets[0].sort(c => c.date)[0].date
        fillPageWithAssets()
    }
}

function upDatePriceHistoryGraph(priceHistoriesOfAsset, width, height) {
    const asset = priceHistoriesOfAsset[0].asset
    const graphContainer = document.getElementById(asset.code).getElementsByClassName(GRAPHCONTAINER_CLASS)[0]
    graphContainer.replaceChild(createPriceHistoryGraph(asset, priceHistoriesOfAsset, width, height), graphContainer.firstChild)
}

function updateAllPriceHistoryGraphs() {
    for (const priceHistoriesOfAsset of priceHistoriesByAssets) {
        upDatePriceHistoryGraph(priceHistoriesOfAsset, GRAPHWIDTH, GRAPHHEIGHT)
    }
}

daysBackInputField.addEventListener("input", async () => {
    daysBack = daysBackInputField.value
    updateAllPriceHistoryGraphs();
})

function normalizePrice(currentPrice) {
    //is de prijs ver achter de komma? Dan wordt 1/currentPrice groot
    //Hoe ver achter de komma? neem er de log10 van en rond de factor af.
    let factor = Math.round(Math.log10(1 / currentPrice))
    // als de factor groter dan 1 is (dus getal is meer dan een nul achter de komma)
    //corrigeer het aantal digits achter de komma met die factor. Als factor kleiner dan 1 is rond dan af op het
    //standaard aantal cijfers achter de komma(ROUNDING_DIGITS)
    let multiPly = (factor > 1 ? Math.pow(10, factor) : 1) * Math.pow(10, ROUNDING_DIGITS)
    return (Math.round(currentPrice * multiPly) / multiPly)
}

function createPriceHistoryGraph(asset, priceHistoriesOfAsset, width, height) {
    const dateInPast = new Date(new Date().valueOf() - daysBack * 86400000)
    priceHistoriesOfAsset = priceHistoriesOfAsset.filter(ph => ph.date > dateInPast)
    let priceHistoryGraph = createGraph(priceHistoriesOfAsset, width, height)
    priceHistoryGraph.id = PRICEHISTORYGRAPH_ID_PREFIX + asset.code
    priceHistoryGraph.className = PRICEHISTORYGRAPH_CLASS
    return priceHistoryGraph;
}

function addEventListenner(assetContainer) {
    const priceHistoryByAsset = priceHistoriesByAssets.filter(ph => ph[0].asset.code === assetContainer.id)[0]
    assetContainer.addEventListener("click", () => {
        if (assetContainer.className === SELECTED_ASSETCONTAINER_CLASS) {
            assetContainer.className = ASSETCONTAINER_CLASS
            upDatePriceHistoryGraph(priceHistoryByAsset, GRAPHWIDTH, GRAPHHEIGHT)
        } else {
            for (const assetContainer of MARKETPLACE_ROOT_CONTAINER.getElementsByClassName(SELECTED_ASSETCONTAINER_CLASS)) {
                const priceHistoryByAsset = priceHistoriesByAssets.filter(ph => ph[0].asset.code === assetContainer.id)[0]
                assetContainer.className = ASSETCONTAINER_CLASS
                upDatePriceHistoryGraph(priceHistoryByAsset, GRAPHWIDTH, GRAPHHEIGHT)
            }
            assetContainer.className = SELECTED_ASSETCONTAINER_CLASS


            upDatePriceHistoryGraph(priceHistoryByAsset, 600, 300)

        }
    })
}

function fillAssetContainer(assetContainer, priceHistoriesByAsset) {
    if (priceHistoriesByAsset.length > 0) {
        priceHistoriesByAsset.sort(c => c.date)
        const asset = priceHistoriesByAsset[0].asset
        assetContainer.id = asset.code;
        assetContainer.getElementsByClassName(ASSETCODELABEL_ID)[0].innerHTML = asset.code
        assetContainer.getElementsByClassName(ASSETNAMELABEL_ID)[0].innerHTML = asset.name
        assetContainer.getElementsByClassName(ASSETCURRENTPRICELABEL_ID)[0].innerHTML = normalizePrice(asset.currentPrice)
        const graphContainer = assetContainer.getElementsByClassName(GRAPHCONTAINER_CLASS)[0]
        if (graphContainer.firstChild !== null) {
            graphContainer.replaceChild(createPriceHistoryGraph(asset, priceHistoriesByAsset, GRAPHWIDTH, GRAPHHEIGHT), graphContainer.firstChild)
        } else {
            graphContainer.appendChild(createPriceHistoryGraph(asset, priceHistoriesByAsset, GRAPHWIDTH, GRAPHHEIGHT))
        }
        assetContainer.getElementsByClassName(TRADEBUTTON_CLASS)[0].addEventListener("click", function () {
            localStorage.setItem(CURRENT_ASSET_KEY, JSON.stringify(asset))
            window.location.href = tradeButtonLink;
        })
    }
    addEventListenner(assetContainer)
}

const fillPageWithAssets = () => {
    const assetContainers = document.getElementsByClassName("assetContainer")
    for (let i = 0; i < assetContainers.length; i++) {
        fillAssetContainer(assetContainers[i], priceHistoriesByAssets[i])
    }
}

function parseDate(localDateTimeString) {
    const dateTime = localDateTimeString.substring(0, 10) + " " + localDateTimeString.substring(11)
    return new Date(dateTime)
}

function saveAsPriceHistories(jsonPriceHistories) {
    const tempPriceHistoriesByAssets = []
    for (const priceHistoriesOfAssetJson of jsonPriceHistories) {
        const priceHistoriesOfAsset = []
        for (const priceHistory of priceHistoriesOfAssetJson) {
            priceHistoriesOfAsset.push(new PriceHistory(parseDate(priceHistory.dateTime), priceHistory.price, priceHistory.asset))
        }
        tempPriceHistoriesByAssets.push(priceHistoriesOfAsset)
    }
    priceHistoriesByAssets = tempPriceHistoriesByAssets
}

function createDateInPast() {
    const date = new Date(new Date().valueOf() - daysBack * 86400000)
    return date.toISOString().substring(0, 23);
}

const getPriceHistoriesByAsset = (token) => {
    return fetch(`${rootURL}marketplace`,
        {
            method: 'POST',
            headers: acceptHeadersWithToken(token),
            body: createDateInPast()
        }).then(promise => {
        if (promise.ok) {
            return promise.json()
        } else {
            console.log("Couldn't retrieve pricehistory from the server")
        }
    }).then(json => json)
}

await initializePage()
const dateNow = new Date()
const initialTimeOut = updateInterval - (dateNow.getTime() - lastUpdate.getTime()) + 10000
console.log(initialTimeOut)
const onPriceHistoryUpdate = () => {
    initializePage()
    setInterval(initializePage, updateInterval)
}

setTimeout(onPriceHistoryUpdate, initialTimeOut)

