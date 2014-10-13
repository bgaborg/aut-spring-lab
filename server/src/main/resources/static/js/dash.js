$(function() {
    console.log("I'm alive");
});

window.dashUtil = new DashUtil();

var DashUtil = function(){
    var self = this;

    self.calAjax = function(url){
        return $.ajax(url);
    }

};


