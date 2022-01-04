function setData(dates, prices) {
    return {
        labels: dates,
        datasets: [{
            legend: {display: false},
            pointBackgroundColor: "white",
            backgroundColor: "white",
            borderColor: "rgba(0,0,0,0.5)",
            data: prices,
            fill: false
        }]
    }
}

const createGraph = (priceHistoriesOfAsset) => {
    let dates = priceHistoriesOfAsset.map(ph => ph.date)
    let prices = priceHistoriesOfAsset.map(ph => ph.price)
    const graphJs = document.createElement("canvas")
    new Chart(graphJs, {
        type: "line",
        data: setData(dates, prices),
        options: {
            spanGaps: true,
            datasets: {
                line: {
                    pointRadius: 1 // disable for all `'line'` datasets
                }
            }
        }
    });
    return graphJs
}
