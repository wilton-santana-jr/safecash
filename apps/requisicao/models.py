from django.db import models
from django.contrib.auth.models import User
from apps.conta.models import *

class Requisicao(models.Model):
    usuario = models.ForeignKey(User, related_name='requisicoes')
    assunto = models.CharField(max_length=100)
    descricao = models.TextField()
    data = models.DateField()
    status = models.CharField(max_length=50)
    tipo = models.IntegerField(default=0)
    valor = models.FloatField(default=0)

    class Meta:
        db_table = 'requisicao'

class RequisicaoContrato(models.Model):
    requisicao = models.OneToOneField(Requisicao)
    responsavel = models.CharField(max_length=100)
    nome_projeto = models.CharField(max_length=100)
    endereco = models.CharField(max_length=100)
    cpf_cnpj = models.CharField(max_length=100)
    observacao = models.TextField()

    class Meta:
        db_table = 'requisicao_contrato'

class RequisicaoReembolso(models.Model):
    requisicao = models.OneToOneField(Requisicao)
    conta = models.OneToOneField(Conta)

    class Meta:
        db_table = 'requisicao_reembolso'
