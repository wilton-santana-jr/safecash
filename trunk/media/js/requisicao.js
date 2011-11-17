$(document).ready(
		function() {
			
			var idPedido;
			
// >>>>> ABRIR O POP UP QUE VAI INSERIR A REQUISIÇÃO
// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
			$(".inserirReq").click(function(e) {
			


                mostrarPopup('#popUpInsertReq');

			});



function mostrarPopup(popup)
    {
        
        var largura = $('body').outerWidth(true);
            var altura = $('body').outerHeight(true);
            var alturaJanela = $(window).height();
            
            $('#tela').css({
                width: largura,
                height: altura
            });
            $('#tela').fadeIn(200);
            $(popup).css({
                top: alturaJanela / 2 - $(popup).height() / 2,
                left: largura / 2 - $(popup).width() / 2
            });
            $(popup).fadeIn(200);
        
    }
			
// >>>>> AÇÃO DO BOTÃO QUE VAI CRIAR A REQUISIÇÃO
// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
			$(".armazenar").click(function(e) {
			    var date = new Date();
			    
        		var usuarioReq = 1;
                var assuntoReq =  $("#assunto").val();
                var dataReq = date.getDate() + "/" + date.getMonth() + "/" + date.getFullYear();
                var estadoReq = "Pendente";
                var tipoReq = $("select[name=tipoReq]:checked").val();
                var valorReq = $("#valor").val();
                var descricaoReq = $("#popUpInsertReq textarea").val();
               
                
                if(usuarioReq && assuntoReq && valorReq){
                  $.post("inserirReq/", { 
                  usuario: usuarioReq,
                  assunto: assuntoReq,
                  descricao: descricaoReq,
                  data: dataReq,
                  status: estadoReq,
                  tipo : tipoReq,
                  valor : valorReq},
                  
                  function(data) {
					    
                        /*var jSon = jQuery.parseJSON(data)
                        if(jSon.salvo)
                            alert("cadastrado com sucesso")*/
                        document.location.reload();
                        
				}); 
           
                  $('#popUpInsertReq').fadeOut(200);
                  $('#tela').fadeOut(200);
                } else {
                    alert('Não foi possível fazer a requisição!');
                }
                 // $("#popUpInserir textarea").val(nomeModelo + enderecoModelo+descricaoModelo );
                 
                 //$('#tudo').html(pagina);
             });
             
// >>>>> FECHAR POP UP QUE INSERE UMA REQUISICAO
// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
             $(".fecharInsertReq").click(function(e) {
                    $('#popUpInsertReq').fadeOut(200);
                    $('#tela').fadeOut(200);
             });
             
             
			
			$('span.requisitante a').click(function(e) {
				
				var largura = $('body').outerWidth(true);
				var altura = $('body').outerHeight(true);
				var alturaJanela = $(window).height();

				// $('body').text(id);
					$('#tela').css( {
						width : largura,
						height : altura
					});
					
					
					$('#tela').fadeIn(200);
					idPedido = $(this).attr('id');
					var tipoRequisicao = $("input[name=" + idPedido + "]:hidden").val();
					var titulo = $(this).attr("title");
					
					
					$('#popUpGeral').css({top:alturaJanela/2 -$('#popUpGeral').height()/2,left:largura/2 - $('#popUpGeral').width()/2});							
					$('#nomePopUpGeral').text(titulo);
					$('#informacoesGeral input:hidden').val(idPedido);
					
					var typeVar = 'list';
					var sourceVar = 'geral';
					
					$.post("visualizar/", {
						type : typeVar,
						source : sourceVar,
						chave : idPedido
					}, function(data) {
						$("#descricaoGeral").text(data.descricao);
					},  'json');
					
					$('#popUpGeral').fadeIn(200);
					

					
					$('.informativo').hide();

				});

			$(".fecharGeral").click(function(e) {
				// $('div.calendario').hide(200);
					$('#popUpGeral').fadeOut(200);
					$('#tela').fadeOut(200);

				});
			$(".fecharContrato").click(function(e) {
				// $('div.calendario').hide(200);
					$('#popUpContrato').fadeOut(200);
					$('#tela').fadeOut(200);

				});

			$(".salvarGeral").click(function(e) {

				var typeVar = 'alter';
				var sourceVar = 'geral';
				var estadoRequisicao = $("#informacoesGeral select").val();
				$.post("salvarEstado/", {
					type : typeVar,
					source : sourceVar,
					chave : idPedido,
					status : estadoRequisicao
				}, function(data) {
					$("#" + data.id + "_estado").text(data.estado);
					$("#" + data.id + "_estado").attr("class", data.classe);
				}, 'json');
				
				$('#popUpGeral').fadeOut(200);
				$('#tela').fadeOut(200);

			});
			
			$(".salvarContrato").click( function(e) {

				var typeVar = 'alter';
				var sourceVar = 'contrato';
				var estadoRequisicao = $("#informacoesContrato select").val();
				
				$.post("salvarEstado/", {
					type : typeVar,
					source : sourceVar,
					chave : idPedido,
					status : estadoRequisicao
				}, function(data) {
					$("#" + data.id + "_estado").text(data.estado);
					$("#" + data.id + "_estado").attr("class", data.classe);
				}, 'json');
				
				$('#popUpContrato').fadeOut(200);
				$('#tela').fadeOut(200);
			});

			$(".responderGeral").click(function(e) {

				var idRequisicao = $('#informacoesGeral input:hidden').val();
				var estadoRequisicao = $("#informacoesGeral select").val();
				var pagina = $.post("nomedomapeamento", {
					id : idRequisicao,
					estado : estadoRequisicao
				});
				$('tudo').text(pagina);

				$('#popUpGeral').fadeOut(200);
				$('#tela').fadeOut(200);

			});
			
			$(".remover").click(function(e) {
				$.post("remove/", { chave : idPedido }, function (data) {
				    document.location.reload(); 
                });
			});
			$(".responderContrato").click(
					function(e) {

						var idRequisicao = $(
								'#informacoesContrato input:hidden').val();
						var estadoRequisicao = $("#informacoesGeral select")
								.val();
						var pagina = $.post("nomedomapeamento", {
							id : idRequisicao,
							estado : estadoRequisicao
						});
						$('tudo').text(pagina);
						$('#popUpContrato').fadeOut(200);
						$('#tela').fadeOut(200);

					});
		});

// JavaScript Document
