$(document).ready(
		function() {

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
					var idPedido = $(this).attr('id');
					
					alert(idPedido);
					
					var tipoRequisicao = $("input[name=" + idPedido + "]:hidden").val();
					
					alert(tipoRequisicao);

					/*
					 * $.post("nomedomapeamento", { id: idPedido,pedido: true}
					 * );retornar um caracter referente ao tipo de requisição C =>
					 * contrato,G=> geral
					 */
					/*
					 * var nomeRequisitante = $.post("nomedomapeamento", { id:
					 * idPedido,nome: true} );/*retornar o nome do requisitante
					 */
					/*
					 * var descricaoRequisicao = $.post("nomedomapeamento", {
					 * id: idPedido,descricao: true} );/*retornar uma lista com
					 * os pontos de descrição da requisição(na mesma formatação
					 * da div popUp)
					 */

					if (tipoRequisicao == 'c') {
						$('#popUpContrato').css(
								{
									top : alturaJanela / 2 - $('#popUpContrato').height() / 2,
									left : largura / 2 - $('#popUpContrato').width() / 2
								});
						// chamar o método post!!!!!!!
						$('#nomePopUpContrato').text('Requisição de Contrato:' + nomeRequisitante);
						$('#informacoesContrato').html(descricaoRequisicao);
						$('#informacoesContrato input:hidden').val(idPedido);
						$('#popUpContrato').fadeIn(200);

					} else if (tipoRequisicao == 'g') {
						$('#popUpGeral').css(
								{
									top : alturaJanela / 2 - $('#popUpGeral').height() / 2,
									left : largura / 2 - $('#popUpGeral').width() / 2
								});
						// chamar o método post!!!!!!!
						$('#nomePopUpGeral').text('Requisição Geral:' + nomeRequisitante);
						$('#informacoesGeral').html(descricaoRequisicao);
						$('#informacoesGeral input:hidden').val(idPedido);
						$('#popUpGeral').fadeIn(200);
					}

					// $('#nomePopUp').text(modelo);
					// $('#PopUp p').text(descricao);
					// $('#valor').val( idModelo);

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
			$(".salvarContrato").click(
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

			$(".baixarContrato").click(
					function(e) {

						var idRequisicao = $(
								'#informacoesContrato input:hidden').val();
						var estadoRequisicao = $("#informacoesGeral select")
								.val();
						var pagina = $.post("nomedomapeamento", {
							id : idRequisicao,
							estado : estadoRequisicao
						});

					});

		});

// JavaScript Document
