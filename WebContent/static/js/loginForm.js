/**
 * 
 */

function inputMsgClear(obj){
			if (obj){
				var $obj = $(obj);
				$obj.parent().removeClass("error");
				$obj.parent().removeClass("positive");
				$obj.parent().next(".inputmsg").remove();
			} else {
				$("label").removeClass("error");
				$("label").removeClass("positive");
				$(".inputmsg").remove();
			}
		}