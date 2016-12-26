/**
 * Created by haolin on 3/26/15.
 * Jquery函数扩展
 */
$.extend({
    /**
     * get all url params as an object
     */
    getUrlVars: function() {
        var vars = {}, hash;
        var href =  window.location.href;
        var index = href.indexOf('?');
        if (index == -1){
            return {};
        }
        var hashes = href.slice(index + 1).split('&');
        for (var i = 0; i < hashes.length; i++) {
            hash = hashes[i].split('=');
            vars[hash[0]] = decodeURI(hash[1]);
        }
        return vars;
    },
    /**
     * get val of param[name]
     */
    getUrlVar: function(name) {
        return $.getUrlVars()[name];
    },
    /**
     * get the url without params
     */
    getUrlPath: function(){
        var href = window.location.href;
        var index = href.indexOf('?');
        if (index == -1) {
            return href + '?';
        } else{
            return href.substring(0, index) + '?';
        }
    },
    loadCss: function(href){
        var link = document.createElement('link');
        link.type = 'text/css';
        link.rel = 'stylesheet';
        link.href = href;
        document.getElementsByTagName("head")[0].appendChild(link);
    },
    loadJs: function(src){
        var script = document.createElement('script');
        script.type = 'text/javascript';
        script.src = src;
        document.getElementsByTagName("head")[0].appendChild(script);
    },
    fmtDate: function(date){
        var d = new Date(date);
        var m = d.getMonth() + 1;
        m = m < 10 ? '0' + m : m;
        var day = d.getDate();
        day = day < 10 ? '0' + day : day;
        var h = d.getHours();
        h = h < 10 ? '0' + h : h;
        var min = d.getMinutes();
        min = min < 10 ? '0' + min : min;
        var s = d.getSeconds();
        s = s < 10 ? '0' + s : s;

        return d.getFullYear() + '-' + m + '-' + day + ' ' + h + ':' + min + ':' + s;
    }
});
