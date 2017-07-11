Highcharts.chart('graph', {
  chart: {
    type: 'column'
  },
  title: {
    text: 'Monthly Site Visiting'
  },
  subtitle: {
    text: 'classified by type of user'
  },
  xAxis: {
    categories: [
      'Jan',
      'Feb',
      'Mar',
      'Apr',
      'May',
      'Jun',
      'Jul',
      'Aug',
      'Sep',
      'Oct',
      'Nov',
      'Dec'
    ],
    crosshair: true
  },
  yAxis: {
    min: 0,
    title: {
      text: 'Quantities (user)'
    }
  },
  tooltip: {
    headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
    pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
      '<td style="padding:0"><b>{point.y:.1f} user</b></td></tr>',
    footerFormat: '</table>',
    shared: true,
    useHTML: true
  },
  plotOptions: {
    column: {
      pointPadding: 0.2,
      borderWidth: 0
    }
  },
  series: [{
    name: 'Guest',
    data: [49, 71, 106, 129, 144, 176, 135, 148, 216, 194, 95, 54]

  }, {
    name: 'Student',
    data: [83, 78, 98, 93, 106, 84, 105, 104, 91, 83, 106, 92]

  }, {
    name: 'Professor',
    data: [48, 38, 39, 41, 47, 48, 59, 59, 52, 65, 59, 51]

  }, {
    name: 'Staff',
    data: [42, 33, 34, 39, 52, 75, 57, 60, 47, 39, 46, 51]

  }]
});

Highcharts.chart('graph2', {
  chart: {
    plotBackgroundColor: null,
    plotBorderWidth: null,
    plotShadow: false,
    type: 'pie'
  },
  title: {
    text: 'Monthly Site Visiting'
  },
  subtitle: {
    text: 'classified by type of browser'
  },
  tooltip: {
    pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
  },
  plotOptions: {
    pie: {
      allowPointSelect: true,
      cursor: 'pointer',
      dataLabels: {
        enabled: false
      },
      showInLegend: true
    }
  },
  series: [{
    name: 'Brands',
    colorByPoint: true,
    data: [{
      name: 'Microsoft Internet Explorer',
      y: 56.33
    }, {
      name: 'Chrome',
      y: 24.03,
      sliced: true,
      selected: true
    }, {
      name: 'Firefox',
      y: 10.38
    }, {
      name: 'Safari',
      y: 4.77
    }, {
      name: 'Opera',
      y: 0.91
    }, {
      name: 'Proprietary or Undetectable',
      y: 0.2
    }]
  }]
});
