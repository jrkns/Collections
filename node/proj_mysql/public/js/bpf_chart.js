var dataByYear = {
  series: [y1, y2, y3, y4, y5]
};

var dataByStatus = {
  series: [pro, leave, abroad]
};

var sum5 = function(a, b, c, d, e) { return a + b + c + d + e};
var sum3 = function(a, b, c) { return a + b + c};

new Chartist.Pie('#byyear', dataByYear, {
  donut: true,
  donutWidth: 60,
  donutSolid: true,
  startAngle: 270,
  total: sum5(dataByYear.series[0],dataByYear.series[1],dataByYear.series[2],dataByYear.series[3],dataByYear.series[4]),
  showLabel: true,
  height: 300,
  width: 400
});

new Chartist.Pie('#bystatus', dataByStatus, {
  donut: true,
  donutWidth: 60,
  donutSolid: true,
  startAngle: 270,
  total: sum3(dataByStatus.series[0],dataByStatus.series[1],dataByStatus.series[2]),
  showLabel: true,
  height: 300,
  width: 400
});
