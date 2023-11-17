<script>

    function getParams() {
        return  new URLSearchParams({
            "link": window.location.href,
        });
    }

    function changeSubscriptionButton(data) {
        var parent = document.getElementById("subscription-div");
        var child = document.getElementById("subscribe-button");
        parent.removeChild(child);
        parent.innerHTML = data;
    }

    function changeStateBtn() {
        $.ajax({
            url: 'http://localhost:8080/WebProjectScience_war_exploded/ajax/profile',
            method: 'post',
            type: "post",
            data: getParams().toString(),
            success: function(data){
                changeSubscriptionButton(data);
            }
        });
    }

    $(document).ready(function (){
        $("#subscribe-button").click(function (){
            changeStateBtn();
        })
    });
</script>
