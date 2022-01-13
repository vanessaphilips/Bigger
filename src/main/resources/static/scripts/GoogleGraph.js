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

function setOptions(width, height) {
    return {
        title: {display: false},
        curveType: 'function',
        legend: {display: false},
        height: height,
        width: width,
        //        chartArea: {
        //            // leave room for y-axis labels
        //
        //            width: '50%',
        //            height: '50%'
        //        },
        // explorer: {
        //     actions: ['dragToZoom', 'rightClickToReset'],
        //     axis: 'horizontal',
        //     keepInBounds: true,
        //     maxZoomIn: 1100.0
        // }
    };
}

const createGraph = (priceHistoriesOfAsset, width, height) => {
    let dates = priceHistoriesOfAsset.map(ph => ph.date)
    let prices = priceHistoriesOfAsset.map(ph => ph.price)
    const divGraph = document.createElement("div")
    google.charts.load('current', {'packages': ['corechart']});
    google.charts.setOnLoadCallback(drawChart);

    function drawChart() {
        const data = new google.visualization.DataTable()
        setData(data, dates, prices)
        console.log("laden data googleGraph gelukt")
        const options = setOptions(width, height)
        const chart = new google.visualization.LineChart(divGraph);
        chart.draw(data, options);

    }

    return divGraph
}
