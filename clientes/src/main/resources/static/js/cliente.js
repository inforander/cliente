$(function() {
	$('[rel="tooltip"]').tooltip();
	
	$('.js-cpf').keydown(function(){
	    try {
	        $('.js-cpf').unmask();
	    } catch (e) {}

	    var tamanho = $('.js-cpf').val().length;

	    if(tamanho < 11){
	        $('.js-cpf').mask("999.999.999-99");
	    } else if(tamanho >= 11){
	        $('.js-cpf').mask("99.999.999/9999-99");
	    }

	    var elem = this;
	    setTimeout(function(){
	        elem.selectionStart = elem.selectionEnd = 10000;
	    }, 0);
	    var currentValue = $(this).val();
	    $(this).val('');
	    $(this).val(currentValue);
	});

	$('.js-atualizar-status').on(
			'click',
			function(event) {
				event.preventDefault();

				var botaoAtivar = $(event.currentTarget);
				var urlAtivar = botaoAtivar.attr('href');

				var response = $.ajax({
					url : urlAtivar,
					type : 'PUT'
				});

				response.done(function(e) {
					var codigoCliente = botaoAtivar.data('codigo');
					$('[data-role=' + codigoCliente + ']').html(
							'<span class="label label-success">' + e
									+ '</span>');
					botaoAtivar.hide();
				});

				response.fail(function(e) {
					console.log(e);
					alert('Erro ativando cliente');
				});

			});
});
