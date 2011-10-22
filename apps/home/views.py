#coding:utf-8

from django.http import HttpResponse, HttpResponseRedirect
from django.shortcuts import render_to_response
from django.template import RequestContext
from django.utils import simplejson

from django.contrib.auth import authenticate

from apps.home.models import *
from django.contrib.auth.models import User

def login (request):
    return render_to_response("login.html")
    
#def check_login (request):
#    return render_to_response("login.html")

def home(request):
    #todos = [{'id': 10, 'nome': 'meu primeiro modelo'}]

    return render_to_response(
        "pageAdmin.html",
        { 'usuarios': User.objects.all() },
        context_instance=RequestContext(request)
    );

def add_usr(request):
    error = False
    msg = ''
    
    try:
        user = User.objects.get(username=request.POST['login'])

        error = True
        msg = "O usuário já está cadastrado!"
        
    except User.DoesNotExist:
        user = User.objects.create(username=request.POST['login'], password=request.POST['senha'], first_name=request.POST['nome'])
    
        if request.POST['permissao'] == 'administrador':
            user.is_staff = True  
            
        user.save()
    
    toSend = { 'error': error, 'msg': msg }
    return HttpResponse(simplejson.dumps(toSend), mimetype='application/javascript')

def remove_usr(request):
    error = False
    msg = ''
    
    try:
        user = User.objects.get(username=request.POST['login'])
        User.delete(user)
        
    except User.DoesNotExist:
        error = True
        msg = "O usuário não está cadastrado!"
        
    
    toSend = { 'error': error, 'msg': msg }
    return HttpResponse(simplejson.dumps(toSend), mimetype='application/javascript')
    
def edit_usr(request):
    error = False
    msg = ''
    
    try:
        user = User.objects.get(username=request.POST['login'])
        
        user.first_name = request.POST['nome']
        user.password = request.POST['senha']
        
        if request.POST['permissao'] == 'administrador':
            user.is_staff = True
        else:
            user.is_staff = False 
        
        user.save()
        
    except User.DoesNotExist:
        error = True
        msg = "O usuário não está cadastrado!"
        
    
    toSend = { 'error': error, 'msg': msg }
    return HttpResponse(simplejson.dumps(toSend), mimetype='application/javascript')
