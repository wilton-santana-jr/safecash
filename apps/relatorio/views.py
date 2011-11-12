from django.shortcuts import render_to_response, get_object_or_404
from django.template import RequestContext
from django.http import HttpResponse, HttpResponseRedirect
from apps.conta.models import *
import datetime

def home(request):
    transacoes = Transacao.objects.filter(pago = 0)#mudar para 1 depois
    lista_anos = []
    for transacao in transacoes:
        data = transacao.data_vencimento
        if not (data.year in lista_anos):
            lista_anos.append(data.year)
    
    lista_anos.sort()     
    return render_to_response("PageRelatorios.html",{'anos' : lista_anos} ,context_instance=RequestContext(request));

def relatorio(request):
    primeiro_semestre = ["Jan","Fev","Mar","Abr","Mai","Jun"]
    segundo_semestre = ["Jul","Ago","Set","Out","Nov","Dez"]
    transacoes = []
    transacoes_saida = []
    transacoes_calculada = []
    transacoes_saida_calculada = []
    dic_dados = {}
    somatorio_total = 0.0;
    titulo_grafico =""
    anos_requeridos = []    
    anos = []
    dados = []    
    periodo = request.GET['periodo']
    tipo_grafico = request.GET['tipo_grafico']    
    for ano in request.GET.getlist('anos'):
        anos_requeridos.append(ano)
    
    if( anos_requeridos != []):
        for ano in anos_requeridos:        
            if periodo == '0':
                dic_dados[ano] = {1:0.0,2:0.0,3:0.0,4:0.0,5:0.0,6:0.0,7:0.0,8:0.0,9:0.0,10:0.0,11:0.0,12:0.0}
                meses_duracao = primeiro_semestre + segundo_semestre
            elif periodo =='1':
                dic_dados[ano] = {1:0.0,2:0.0,3:0.0,4:0.0,5:0.0,6:0.0}
                meses_duracao = primeiro_semestre
            else:
                dic_dados[ano] = {7:0.0,8:0.0,9:0.0,10:0.0,11:0.0,12:0.0}
                meses_duracao = segundo_semestre

        if request.GET['tipo_relatorio'] == '0' :#faturamento
            titulo_grafico = 'Relatorio de Faturamento'        
            contas_entrada = Conta.objects.filter(tipo=0)
            for conta_entrada in contas_entrada:
                transacoes += Transacao.objects.filter(pago = 1, conta = conta_entrada).order_by('data_vencimento')#mudar pago para um

            for transacao in transacoes:
                data_transacao = transacao.data_vencimento#mudar depois para data_pagamneto
                if str(data_transacao.year) in dic_dados.keys() and data_transacao.month in dic_dados[str(data_transacao.year)].keys():
                    dic_dados[str(data_transacao.year)][data_transacao.month] += transacao.valor
                    somatorio_total += transacao.valor
                    transacoes_calculada.append( transacao)
            
        elif request.GET['tipo_relatorio'] == '1' :#despesas
            titulo_grafico = "Relatorio de Despesas"
            contas_saida = Conta.objects.filter(tipo=1)
            for conta_saida in contas_saida:
                transacoes_saida += Transacao.objects.filter(pago = 1, conta = conta_saida).order_by('data_vencimento')#mudar pago para um

            
            for transacao in transacoes_saida:
                data_transacao = transacao.data_vencimento#mudar depois para data_pagamneto
                if str(data_transacao.year) in dic_dados.keys() and data_transacao.month in dic_dados[str(data_transacao.year)].keys():
                    dic_dados[str(data_transacao.year)][data_transacao.month] += transacao.valor
                    somatorio_total += transacao.valor    
                    transacoes_saida_calculada.append(   transacao)
            
        else:#lucro
            titulo_grafico = "Relatorio de Lucro"
            contas_entrada = Conta.objects.filter(tipo=0)
            contas_saida = Conta.objects.filter(tipo=1)
            for conta_entrada in contas_entrada:
                transacoes += Transacao.objects.filter(pago = 1, conta = conta_entrada).order_by('data_vencimento')#mudar pago para 1
            for conta_saida in contas_saida:
                transacoes_saida += Transacao.objects.filter(pago = 1, conta = conta_saida).order_by('data_vencimento')#mudar pago para 1

            for transacao in transacoes:
                data_transacao = transacao.data_vencimento#mudar depois para data_pagamneto
                if str(data_transacao.year) in dic_dados.keys() and data_transacao.month in dic_dados[str(data_transacao.year)].keys():
                    dic_dados[str(data_transacao.year)][data_transacao.month] += transacao.valor
                    somatorio_total += transacao.valor    
                    transacoes_calculada.append(transacao)
            for transacao in transacoes_saida:
                data_transacao = transacao.data_vencimento#mudar depois para data_pagamneto
                if str(data_transacao.year) in dic_dados.keys() and data_transacao.month in dic_dados[str(data_transacao.year)].keys():
                    dic_dados[str(data_transacao.year)][data_transacao.month] -= transacao.valor
                    somatorio_total -= transacao.valor    
                    transacoes_saida_calculada.append(transacao)

        meses_duracao = listString_to_string(meses_duracao) 
        
        for k ,i in dic_dados.iteritems():
            string_dados = dicNum_to_string(i)
            dados.append((k,string_dados))
        
    
    return render_to_response("relatorio.html", {'titulo': titulo_grafico,'anos':anos,'dados_anos':dados,'meses_duracao':meses_duracao,'tipo_grafico' : tipo_grafico,'transacoes':transacoes_calculada,'transacoes_saida':transacoes_saida_calculada},context_instance=RequestContext(request));


def listString_to_string(lista):
    tam = len(lista)   
    string = "["
    for item in lista:
        if tam > 1:
            string += "\'" + item +"\'," 
            tam -= 1
        else:
            string += "\'" + item +"\'"    
    string += "]"
    return string

def dicNum_to_string(lista):
    tam = len(lista)
    string = "["
    for key, item in lista.iteritems():
        if tam > 1:
            string +=  str(item) + ","
            tam -= 1
        else:
            string +=  str(item)
    string += "]"

    return string
