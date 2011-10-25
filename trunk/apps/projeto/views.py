# Create your views here.
from django.shortcuts import render_to_response, get_object_or_404
from django.template import RequestContext
from django.http import HttpResponse, HttpResponseRedirect
from django.utils import simplejson
from apps.projeto.models import Projeto
from apps.conta.models import Conta
import datetime


#toSend = { 'desc' : descricao }
 #   return HttpResponse(simplejson.dumps(toSend), mimetype='application/javascript')
    

def projeto(request):
#    entradas = Conta.objects.filter(tipo=0).order_by('data')
 #   saidas = Conta.objects.filter(tipo=1).order_by('data')
    projetos = Projeto.objects.order_by('data_inicio');
    
    ano = [[],[],[],[],[],[],[],[],[],[],[],[] ]
    #janeiro = []
    #fevereiro = []
    #marco = []
    #abril = []
    #maio = []
    #junho = []
    #julho = []
    #agosto = []
    #setembro = []
    #outubro = []
    #novembro = []
    #dezembro = []

    #print projetos
    for projeto in projetos:
        ano[projeto.data_inicio.month - 1].append( projeto)
    #print "vou imprimir a putaria!!"
    #print ano
    return render_to_response("PageProjetos.html",{"projetos_ano" : ano}, context_instance=RequestContext(request));

def cadatro_projeto_ajax(request):    
    
    print request.method
    if request.method == 'POST':
        nome = request.POST.get('nome_projeto',"")
        valor = float(request.POST.get('valor_projeto',0.0))
        data_inicio = request.POST.get('data_inicio_projeto')
        data_termino = request.POST.get('data_termino_projeto')
        responsavel_projeto = request.POST.get('responsavel_projeto',"")
        descricao_projeto = request.POST.get('descricao_projeto',"")
      
        #if data_termino != "" and data_termino != None:
        data_termino = datetime.datetime.strptime(data_termino, "%d/%m/%Y")
        #if data_inicio !="" and data_inicio != None:
        data_inicio = datetime.datetime.strptime(data_inicio, "%d/%m/%Y")
        
        projeto = Projeto()
        projeto.nome = nome
        projeto.responsavel = responsavel_projeto
        projeto.valor = valor
        projeto.data_inicio = data_inicio
        projeto.data_fim = data_termino
        projeto.descricao = descricao_projeto
      
        projeto.save()
        id_projeto = projeto.id

        conta_saida = Conta()
        
        conta_saida.nome = nome + "-Conta_Saida"
        conta_saida.data = datetime.strptime(request.POST['data_saida'], '%d/%m/%Y')
        conta_saida.valor_total = float(request.POST['valor_saida'])
        conta_saida.parcelas = int(request.POST['parcelas_conta_saida'])
        conta_saida.tipo = 1
        conta_saida.livro = int(request.POST['tipo_saida'])
        conta_saida.descricao = request.POST['descricao_saida']
        conta_saida.save()

        for i in xrange(conta_saida.parcelas):
            transacao = Transacao()
            transacao.conta = conta_saida
            transacao.nome = conta_saida.nome + ': Parcela %s' % i
            transacao.valor = conta_saida.valor_total/conta.parcelas
            transacao.data_vencimento = conta_saida.data + timedelta(i*365/12)
            transacao.save()
    
        conta_entrada = Conta()

        conta_entrada.nome = nome + "-Conta_Entrada"
        conta_entrada.data = datetime.strptime(request.POST['data_entrada'], '%d/%m/%Y')
        conta_entrada.valor_total = float(request.POST['valor_entrada'])
        conta_entrada.parcelas = int(request.POST['parcelas_conta_entrada'])
        conta_entrada.tipo = 0
        conta_entrada.livro = int(request.POST['tipo_entrada'])
        conta_entrada.descricao = request.POST['descricao_entrada']
        conta_entrada.save()

        for i in xrange(conta_entrada.parcelas):
            transacao = Transacao()
            transacao.conta = conta_entrada
            transacao.nome = conta_entrada.nome + ': Parcela %s' % i
            transacao.valor = conta_entrada.valor_total/conta.parcelas
            transacao.data_vencimento = conta_entrada.data + timedelta(i*365/12)
            transacao.save()

        toSend = { 'salvo' : True }
        return HttpResponse(simplejson.dumps(toSend), mimetype='application/javascript')
    else:
        return HttpResponse() 

