const createGraph = (priceHistoriesOfAsset) => {
    //  <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    //  <script type="text/javascript">
    let dates = priceHistoriesOfAsset.map(ph => ph.date)
    let prices = priceHistoriesOfAsset.map(ph => ph.price)
    const divGraph = document.createElement("div")
    google.charts.load('current', {'packages': ['corechart']});
    google.charts.setOnLoadCallback(drawChart);

    function drawChart() {
        const data = new google.visualization.DataTable()
        //console.log(dates)
        data.addColumn('date', 'date');
        data.addColumn('number', 'price');
        for (let date of dates) {
            const row = []
            row.push(date)
            row.push(prices[dates.indexOf(date)])
            data.addRow(row)
        }
        console.log("laden data googleGraph gelukt")

        var options = {
            title: {display: false},
            curveType: 'function',
            legend: {display: false},
            height: 100,
            width: 200,

        };

        const chart = new google.visualization.LineChart(divGraph);
        chart.draw(data, options);

    }

    return divGraph

}