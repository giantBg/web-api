/**
 * Created by xubing on 16/05/10.
 */
$(document).ready(function () {
    var options = {
        title: {
            text: '',
            subtext: '',
            link: '',
            x: 'center'
        },
        tooltip: {
            trigger: 'item'
        },
        legend: {
            orient: 'vertical',
            x: 'left',
            data: []
        },
        dataRange: {
            min: 0,
            max: 2500,
            x: 'left',
            y: 'bottom',
            text: ['高', '低'],           // 文本，默认为数值文本
            calculable: true
        },
        toolbox: {
            show: true,
            orient: 'vertical',
            x: 'right',
            y: 'center',
            feature: {
                mark: {show: true},
                dataView: {show: true, readOnly: false},
                restore: {show: true},
                saveAsImage: {show: true}
            }
        },
        roamController: {
            show: true,
            x: 'right',
            mapTypeControl: {
                'china': true
            }
        },
        series: []
    };

    //获取地图数据
    function getWebMapData(chart) {
        $.ajax({
            type: 'POST',
            async: true,
            url: '/web/chart/map',
            data: {
                //none
            },
            dataType: 'json',
            success: function (result) {
                options.title.text = result.title;
                options.title.subtext = result.subTitle;
                options.title.link = result.link;
                options.legend.data = result.legendList;

                var seriesData = result.seriesList;
                for (var i = 0; i < seriesData.length; i++) {
                    var itemStyle = {normal: {label: {show: true}}, emphasis: {label: {show: true}}};
                    seriesData[i].itemStyle = itemStyle;
                }
                options.series = seriesData;
                chart.hideLoading();
                chart.setOption(options);
            },
            error: function (msg) {
                //TODO
            }
        });
    }

    //show map图表
    function renderWebMapChart() {
        var mapChart = echarts.init(document.getElementById("index_map_chart"));
        mapChart.showLoading({text: '正在加载数据......'});
        getWebMapData(mapChart);
    }

    renderWebMapChart();

});

