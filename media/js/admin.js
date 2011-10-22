$(document).ready(function() {

	$('#botao').click(function(e) {

		var largura = $('body').outerWidth(true);
		var altura = $('body').outerHeight(true);
		var alturaJanela = $(window).height();
		
		$('#tela').css( {
			width : largura,
			height : altura
		});
		
		$('#tela').fadeIn(200);
		
		$('#popUp').css( {
			top : alturaJanela / 2 - $('#popUp').height() / 2,
			left : largura / 2 - $('#popUp').width() / 2
		});
		
		$('#popUp').fadeIn(200);

	});

	$(".fechar").click(function(e) {
		$('div.calendario').hide(200);
		$('#popUp').fadeOut(200);
		$('#tela').fadeOut(200);

	});

	$(".removerUsuario").click(function(e) {
		var loginUsrVar = $("input[name='usuario']:checked").val();
		var typeVar = 'remove';
		
		$.post("remove_usr/", { login: loginUsrVar }, function (data) {
		    if (data.error) {
		        alert(data.msg);
		    } else {
		        $('#li_' + loginUsrVar).remove();
		    }
		}, 'json');
	});

	$("#formEditar a").click(function(e) {
		var nomeVar = $("input[name='nomeEdicao']").val();
		var loginVar = $("input[name='loginEdicao']").val();
		var senhaVar = $("input[name='senhaEdicao']").val();
		var tipoEdicaoVar = $("input[name='tipoEdicao']:checked").val();

        $.post("edit_usr/", {
			nome : nomeVar,
			login : loginVar,
			senha : senhaVar,
			permissao : tipoEdicaoVar,
		}, function(data) {
		    if (data.error) {
		        alert(data.msg);
		    } else {
		        $('#li_' + loginVar).replaceWith('<li id="li_' + loginVar + '"><input type="radio" name="usuario" value="' +
                loginVar + '" id="' + loginVar + '"/><label for="' + loginVar + '">' +
                nomeVar + ' (' + loginVar + ')</label></li>');
		    }
		}, 'json');
		
        // LIMPAR CAMPOS CADASTRO
		$('#popUp').find(':input').each(function() {
			switch (this.type) {
			case 'password':
			case 'select-multiple':
			case 'select-one':
			case 'text':
			case 'textarea':
				$(this).val('');
				break;
			case 'checkbox':
			case 'radio':
				this.checked = false;
			}
		});
	});
	
	$("#formCadastrar a").click(function(e) {
		var nomeVar = $("input[name='nomeCadastro']").val();
		var loginVar = $("input[name='loginCadastro']").val();
		var senhaVar = $("input[name='senhaCadastro']").val();
		var tipoCadastroVar = $("input[name='tipoCadastro']:checked").val();
		
		$.post("add_usr/", {
			nome : nomeVar,
			login : loginVar,
			senha : senhaVar,
			permissao : tipoCadastroVar,
		}, function(data) {
		    if (data.error) {
		        alert(data.msg);
		    } else {
		      $('#usuarios ul').append('<li id="li_' + loginVar + '"><input type="radio" name="usuario" value="' +
                loginVar + '" id="' + loginVar + '"/><label for="' + loginVar + '">' +
                nomeVar + ' (' + loginVar + ')</label></li>');
		    }
		}, 'json');

        // LIMPAR CAMPOS CADASTRO
		$('#popUp').find(':input').each(function() {
			switch (this.type) {
			case 'password':
			case 'select-multiple':
			case 'select-one':
			case 'text':
			case 'textarea':
				$(this).val('');
				break;
			case 'checkbox':
			case 'radio':
				this.checked = false;
			}
		});
	});

});
