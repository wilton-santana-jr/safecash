$(document).ready(function() {
       
	
		$('#calendario').calendario({
			target:'#calendario',
			closeClick:false,
			referencePosition : '#popUp'
		});
	
		$('div.calendario').hide();
		$('div.calendario ul li a').click(function(){
			var html = $(this).html();
			$(this).remove();
			
			
			$('div.calendario ul li.default').removeClass('default');
			$('div.calendario ul li:empty').addClass('default');
			$('div.calendario ul li:empty').html('<a href="#">'+html + '</a>');
		});
		
		
	

            $('#botao').click(function(e){
								     
									  var largura = $('body').outerWidth(true);
									  var altura =  $('body').outerHeight(true);
									  var alturaJanela = $(window).height();
									 // var modelo = $(this).text();
									  //var idModelo = $(this).attr('id');
									 // var descricao = $.post("nomedomapeamento", { id: idModelo} );
									  
									 // $('body').text(id);
									  $('#tela').css({width:largura,height:altura});
									  $('#tela').fadeIn(200);
									  $('div.calendario').css({top:alturaJanela/2 -260/2+115,left:largura/2 - 434/2+225});
									  $('div.calendario').fadeIn(200);
									  $('#popUp').css({top:alturaJanela/2 -260/2,left:largura/2 - 434/2});
									  //$('#nomePopUp').text(modelo);
									 // $('#PopUp p').text(descricao);
									 // $('#valor').val( idModelo);
									  $('#popUp').fadeIn(200);
									  $('.informativo').hide();
									  						  										  
								   } );
			
      
		
		$(".fechar").click(function(e){
									$('div.calendario').hide();
									$('#popUp').fadeOut(200);
									$('#tela').fadeOut(200);
																										
									});
		
		/*$(".remover").click(function(e){
									
									  var idModelo =  $('#valor').val();
									var pagina = $.post("nomedomapeamento", { id: idModelo} );
									$('#popUp').fadeOut(200);
									 $('#tela').fadeOut(200);
									 $('body').text(pagina);
									 
									
									
									});*/
		$(".inserir").click(function(e){
									 var origemConta =  $("input[name='origem']:checked").val();
									 var descriacoConta = $("input[name='descricao']").val();
									 var tipoConta = $("input[name='tipo']:checked").val();
									 var dataConta = $('#calendario')
									 var pagina = $.post("nomedomapeamento", { decricao: descriacoConta,origem: origemConta,tipo:tipoConta,data:dataConta} );
									 $('#tudo').text(pagina);
									 $('#popUp').fadeOut(200);
									 $('#tela').fadeOut(200);
									 
									
									
									});
		
    });

