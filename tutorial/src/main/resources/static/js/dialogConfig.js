const dialogConfig = {
  loginError: {
		autoOpen: false,
		width: 550,
		modal: true,
		buttons: [
			{
				text: 'OK',
				click: function() {
					$(this).dialog('close');
				}
			},
		]
	},
	inputDestinationConfirm: {
		autoOpen:false,
		width: 850,
		modal: true,
		buttons: [
			{
				text: '登録',
				click: function() {
					let jsonString = {
						'familyName': $('#familyName'),
						'firstName': $('#firstName'),
						'address': $('#address'),
						'telNumber': $('telNumber')
					};
					$.ajax({
						type: 'POST',
						url: 'tutorial/destination/register',
						data: JSON.stringify(jsonString),
						contentType: 'application/json',
						dataType: 'json',
					}).done(function(data) {
						alert('新規宛先を登録しました');
						location.href='/tutorial/purchase';
					}).fail(function(data) {
						alert('ajax通信に失敗しました');
					}).always(function(data) {
						console.log('ajax通信しました');
					});
					$(this).dialog('close');
				},
			},
			{
				text: '戻って修正',
				click: function() {
					$(this).dialog('close');
				}
			},
		]
	},
};
