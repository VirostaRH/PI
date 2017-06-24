$(document).ready(cargaEventos);


function cargaEventos()
{
	cargaMails();
	generaMail();
}

function cargaMails()
{
	$("span").click(function(e){
		if(!$("textarea").val().includes($(this).text()))
		{
			if($("textarea").val() == "")
			{
				$("textarea").val($(this).text());
			}
			else
			{
				$("textarea").val($("textarea").val() + "," + $(this).text());
			}
		}
	});
}

function generaMail()
{
	$(".enviar").click(function(e){
		
		e.preventDefault();
		var destinos = $("textarea").val();
		if(destinos != "")
		{
			mail = "mailto:"+destinos;
			console.log(mail);
			window.open(mail);
		}
		else
		{
			alert("Es necesario rellenar al menos un destinatario!");
		}
	});
}