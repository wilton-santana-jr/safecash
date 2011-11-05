from django.db import models
from apps.projeto.models import *

class Conta(models.Model):
    projeto = models.ForeignKey(Projeto, null=True, blank=True, related_name='contas')
    nome = models.CharField(max_length=100)
    descricao = models.TextField()
    valor_total = models.FloatField(default=0)
    parcelas = models.IntegerField(default=1)
    tipo = models.IntegerField(default=0)
    livro = models.IntegerField(default=0)
    data = models.DateField()
    #tipo => 0 = entrada , 1 = saida
    #livro => 0 = caixa da empresa , 1 = conta bancaria
    class Meta:
        db_table = 'conta'


class Transacao(models.Model):
    conta = models.ForeignKey(Conta, related_name='transacoes')
    nome = models.CharField(max_length=100)
    valor = models.FloatField(default=0)
    data_vencimento = models.DateField()
    data_pagamento = models.DateField(null=True)
    pago = models.IntegerField(default=0)
    #pago => 0 = pendente, 1 = paga
    class Meta:
        db_table = 'transacao'

