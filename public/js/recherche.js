jQuery(document).ready(function(){

    $(".search").keyup(function(event) {

        //console.log(" test keyup done[+]");
        var nomFormation = $('.search').val();
        var regexp = '\\b(.*)';
        for(var i in nomFormation){
            regexp +='('+nomFormation[i]+')(.*)';
        }
        regexp += '\\b';
        $('#table').find('span').each(function(){

            var reclamationtable = $(this);

            //console.log(reclamationtable.text());
            var res = reclamationtable.text().match(new RegExp(regexp,'i'))
            if(res){
                //console.log("word found + ");
                //console.log(reclamationtable.text());
                reclamationtable.parent().parent().show();

            }
            else{
                reclamationtable.parent().parent().hide();

            }
        })

    })



});