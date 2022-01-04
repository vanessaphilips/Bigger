function setData(data, dates, prices) {
    data.addColumn('date', 'date');
    data.addColumn('number', 'price');
    for (let date of dates) {
        const row = []
        row.push(date)
        row.push(prices[dates.indexOf(date)])
        data.addRow(row)
    }
}

function setOptions() {
    return {
        title: {display: false},
        curveType: 'function',
        legend: {display: false},
        height: 100,
        width: 200,
    };
}

const createGraph = (priceHistoriesOfAsset) => {
    let dates = priceHistoriesOfAsset.map(ph => ph.date)
    let prices = priceHistoriesOfAsset.map(ph => ph.price)
    const divGraph = document.createElement("div")
    google.charts.load('current', {'packages': ['corechart']});
    google.charts.setOnLoadCallback(drawChart);

    function drawChart() {
        const data = new google.visualization.DataTable()
        setData(data, dates, prices)
        console.log("laden data googleGraph gelukt")
        var options = setOptions()
        const chart = new google.visualization.LineChart(divGraph);
        chart.draw(data, options);

    }
    return divGraph
}
