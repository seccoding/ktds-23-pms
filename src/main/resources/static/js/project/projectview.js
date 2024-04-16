$().ready(function () {

    var params = new URLSearchParams(window.location.search);
    var projectId = params.get("prjId");

    var issueChartDataKeyList = []
    var issueChartDataValueList = []

    var requirementChartDataKeyList = []
    var requirementChartDataValueList = []

    $.get("/ajax/project/status/" + projectId, function (response) {
        var chartData = response.data.chartData;

        // Issue 데이터 처리
        chartData.issue.forEach(function(issue) {
            issueChartDataKeyList.push(issue.cmcdName);
            issueChartDataValueList.push(issue.cmcdIdCount);
        });

        // Requirement 데이터 처리
        chartData.requirement.forEach(function(requirement) {
            requirementChartDataKeyList.push(requirement.cmcdName);
            requirementChartDataValueList.push(requirement.cmcdIdCount);
        });

        // 결과 로깅
        console.log("Issue Keys:", issueChartDataKeyList);
        console.log("Issue Values:", issueChartDataValueList);
        console.log("Requirement Keys:", requirementChartDataKeyList);
        console.log("Requirement Values:", requirementChartDataValueList);

        var requirementChart = $("#requirement-chart").get(0).getContext('2d');
        var issueChart = $("#issue-chart").get(0).getContext('2d');

        new Chart(requirementChart, {
                type: 'doughnut',
                data: {
                    labels: requirementChartDataKeyList,
                    datasets: [{
                        data: requirementChartDataValueList,
                        backgroundColor: [ // Optional: you can set different colors for each segment
                            'red', 'blue', 'green', 'yellow', 'purple'
                        ]
                    }]
                },
                options: {
                    responsive: true,
                    plugins: {
                        legend: {
                            position: 'top',
                        },
                        title: {
                            display: true,
                            text: '요구사항 현황'
                        }
                    }
                },
            });

        new Chart(issueChart, {
            type: 'doughnut',
            data: {
                labels: issueChartDataKeyList,
                datasets: [{
                    data: issueChartDataValueList,
                    backgroundColor: [ // Optional: you can set different colors for each segment
                        'red', 'blue', 'green', 'yellow', 'purple'
                    ]
                }]
            },
            options: {
                responsive: true,
                plugins: {
                    legend: {
                        position: 'top',
                    },
                    title: {
                        display: true,
                        text: '이슈 현황'
                    }
                }
            },
        });
    });
})