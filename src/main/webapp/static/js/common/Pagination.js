/**
 * Created by haolin on 3/26/15.
 * 分页组件
 */
var Pagination = function(){
    var render = function(cfg){
        var selector = cfg.selector;
        if (!selector){
            throw Error('你必须设置元素的选择器!');
            return;
        }

        var params = $.getUrlVars();
        if (cfg.params){
            for(var p in cfg.params){
                params[p] = cfg.params[p];
            }
        }
        var range = cfg.range || 5;
        var total = cfg.total || 0;
        if (total == 0){
            return;
        };
        var curPage = params['pageNo'] || 1;
        curPage = parseInt(curPage);
        var pageSize = cfg.pageSize || 10;

        var url = window.location.pathname + '?';

        params['pageSize'] = params['pageSize'] || pageSize;

        var totalPage = (total % pageSize == 0) ? (total / pageSize) : parseInt(total / pageSize) + 1;
        var prePage   = (curPage - 1 < 1) ? 1 : curPage - 1;
        var nextPage  = (curPage + 1 > totalPage) ? totalPage : curPage + 1;
        var startPage = (curPage - range <= 0) ? 1 : curPage - range;
        var endPage   = (curPage + range >= totalPage) ? totalPage : curPage + range;

        var summaryContent = "总共 " + totalPage + " 页, " + total + " 条记录, 每页 " + pageSize + " 条记录";
        var pageContent = "";

        params['pageNo'] = prePage;
        // 上一页
        if (prePage < curPage){
            pageContent += '<li class="disabled"><a href="' + url + $.param(params) + '"><span aria-hidden="true">&laquo;</span><span class="sr-only">Previous</span></a></li>';
        } else {
            pageContent += '<li class="disabled"><a href="javascript:void(0)"><span aria-hidden="true">&laquo;</span><span class="sr-only">Previous</span></a></li>';
        }

        // 页码
        for (var i = startPage; i <= endPage; i++){
            params['pageNo'] = i;
            if (i == curPage){
                pageContent += '<li class="disabled"><a href="javascript:void(0)">' + i + '<span class="sr-only">(current)</span></a></li>';
            } else{
                pageContent += '<li><a href="' + url + $.param(params) + '">' + i + '</a></li>';
            }
        }

        // 下一页
        params['pageNo'] = nextPage;
        if (curPage < nextPage){
            pageContent += '<li><a href="'+ url +$.param(params) +'"><span aria-hidden="true">&raquo;</span><span class="sr-only">Next</span></a></li>';
        } else {
            pageContent += '<li class="disabled"><a href="javascript:void(0)"><span aria-hidden="true">&raquo;</span><span class="sr-only">Next</span></a></li>';
        }

        var allContent =
            '<div class="row">' +
                '<div class="col-md-5 col-sm-12">' +
                    '<div class="dataTables_info">' +
                        summaryContent +
                    '</div>' +
                '</div>' +
                '<div class="col-md-7 col-sm-12">' +
                    '<div class="dataTables_paginate paging_bootstrap">' +
                        '<ul class="pagination">' +
                            pageContent +
                        '</ul>'
                    '</div>' +
                '</div>' +
            '</div>';

        $(selector).append(allContent);
    };

    return {
        render : render
    }
};
