
    var picContent = new Array();

    //上传图片
    function readAsDataURL(){
        var file = document.getElementById("picture").files;
        var fileLength = file.length;
        var reader;
        if(fileLength > 5){
            alert("最多只能选5张噢")
        } else {
            for(var i=0;i<fileLength;i++){
                reader = new FileReader();
                reader.readAsDataURL(document.getElementById("picture").files[i]);
                reader.onload=function(e){
                    picContent[i] = e.target.result;
                };
            }
        }
    }

    //选择心情
    $('.moods i').click(function () {
        if($('.moods').hasClass('happy')){
            $('.happy').css("color","8c8c8c");
            $('.moods').removeClass('happy');
        } else if ($('.moods').hasClass('just')){
            $('.just').css("color","8c8c8c");
            $('.moods').removeClass('just');
        } else {
            $('.bad').css("color","8c8c8c");
            $('.moods').removeClass('bad');
        }
        $(this).css("color","red");
        // $('.moods').addClass($(this).eq(0).attr('class'));
        console.log($(this).eq(0));
    });

    //提交每天说的话
    var iSay = $('#iSay');
    var moods = $('.moods');
    $('.sendBtn').click(function () {
        var iSayContent = iSay.val();
        if(iSayContent.length === 0){
            alert("每天就不想说点什么吗！！！")
        } else {
            if(moods.hasClass('happy') || moods.hasClass('just')  || moods.hasClass('bad')){
                var formData=new FormData();
                formData.append('pic',document.getElementById("picture").files[0])
            } else {
                alert("选择个你今天的心情吧")
            }
        }

    });

    // 放大图片
    $(function () {
        $('.says img').zoomify();
    })