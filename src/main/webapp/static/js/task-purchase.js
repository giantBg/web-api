/**
 * purchaseList.jsp
 * Created by xubing on 2016-08-14.
 */

var TaskPurchase = function () {
    var that = {
        pagingBar: $('.task-purchase-pagination'),
        searchFieldInput: $('.search-field'),
        clearFormBtn: $('.clear-form'),
        searchForm: $('.select-form'),
        sdate: $('#startDate'),
        edate: $('#endDate'),
        timeStComb: $('#st'),
        timeEtComb: $('#et'),
        pageNo: $('.pageno'),
        init: function () {
            // event binding
            that.clearFormBtn.on('click', that.clearFormSubmit);
            that.selectDate();
            that.renderPaging();
        },
        renderPaging: function () {
            new Pagination().render({
                selector: that.pagingBar,
                pageSize: 10,
                total: that.pagingBar.data("total")
            });
        },
        selectDate: function () {
            $(that.timeStComb).datetimepicker({
                language: 'zh-CN',
                format: 'yyyy-MM-dd',
                minView: 'month',
                todayBtn: true,
                autoclose: true
            });
            $(that.timeEtComb).datetimepicker({
                language: 'zh-CN',
                format: 'yyyy-MM-dd',
                minView: 'month',
                todayBtn: true,
                autoclose: true
            });
        },
        clearFormSubmit: function () {
            that.sdate.val("");
            that.edate.val("");
            that.pageNo.val("");
            that.searchFieldInput.val("");
            that.searchForm.submit();
        }
    };
    return that;
}();

// init modules
$(function () {
    $(".js-comp").each(function () {
        window[$(this).data("module")].init();
    });
});