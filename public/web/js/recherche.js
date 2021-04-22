jQuery(document).ready(function(){

    $(".search").keyup(function(event) {

        //console.log(" test keyup done[+]");
        var description = $('.search').val();
        var regexp = '\\b(.*)';
        for(var i in description){
            regexp +='('+description[i]+')(.*)';
        }
        regexp += '\\b';
        $('#table').find('span').each(function(){

            var formationNameInTable = $(this);

            //console.log(formationNameInTable.text());
            var res = formationNameInTable.text().match(new RegExp(regexp,'i'))
            if(res){
                //console.log("word found + ");
                //console.log(formationNameInTable.text());
                formationNameInTable.parent().parent().show();

            }
            else{
                formationNameInTable.parent().parent().hide();

            }
        })

    })



});