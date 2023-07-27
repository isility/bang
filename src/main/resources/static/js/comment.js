function getCommentList() {
        success: function (data) {
            let html = "";
             const count = data.list.length;

            if (data.list.length > 0) {
                for (let i = 0; i < data.list.length; i++) {
                    html += "<div class='mb-2'>";
                    html += "<input type='hidden' id='commentId_"+ data.list[i].id +"' value='" + data.list[i].id + "'>"
                    html += "<b id='commentWriter_" + data.list[i].id + "'>" + data.list[i].writer + "</b>";
                    html += "<span style='float:right;' align='right' id='commentDate_"+ data.list[i].id +"'> " + displayTime(data.list[i].updateDate) + " </span>";
                    html += "<div class='mb-1 comment_container' >"
                    html += "<h5 id='commentText_" + data.list[i].id + "' style='display: inline'>" + data.list[i].comment +"</h5>";
                    html += "<span id='ccCount_" + data.list[i].id + "' style='color: red'> ["+data.commentCnt[i]+"]</span>"
                    html += "</div>"
                    html += "<span style='cursor: pointer; color: blue' class='reCommentBtn' id='reCommentBtn_"+ data.list[i].id +"'>답글 달기 </span>";
                    html += "<span style='display:none; cursor: pointer; color: blue' class='reCommentCloseBtn' id='reCommentCloseBtn_"+ data.list[i].id +"'>답글 닫기 </span>";

                   
                    html += "<hr>";
                    html += "<div class='mx-4 reCommentDiv' id='reCommentDiv_" + data.list[i].id + "'></div></div>";
                }
            } else {
                html += "<div class='mb-2'>";
                html += "<h6><strong>등록된 댓글이 없습니다.</strong></h6>";
                html += "</div>";
            }
            $("#count").html(count);
            $("#commentList").html(html);
        },
        error: function (request, status, error) {
            alert("code: " + request.status + "\n"  + "error: " + error);
        }
    });
}