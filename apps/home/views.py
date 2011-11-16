#coding:utf-8

from django.http import HttpResponse, HttpResponseRedirect
from django.shortcuts import render_to_response, redirect
from django.template import RequestContext
from django.utils import simplejson

from django.contrib.auth import authenticate, login, logout

from apps.home.models import *
from django.contrib.auth.models import User

def index (request):
    return render_to_response(
        "login.html",
        {},
        context_instance=RequestContext(request)
    );


def login_usr (request):
    usr = request.POST['login']
    pw = request.POST['senha']
    
    user = authenticate(username=usr, password=pw)
    
    if user is not None:
        login(request, user)
        return render_to_response(
            "pageAdmin.html",
            { 'usuarios': User.objects.all() },
            context_instance=RequestContext(request)
        );
    else:
        return redirect('/')

def logout_usr (request):
    logout(request)

    return redirect('/')

def add_usr(request):
    error = False
    msg = ''
    
    try:
        user = User.objects.get(username=request.POST['login'])

        error = True
        msg = "O usuário já está cadastrado!"
        
    except User.DoesNotExist:
        user = User.objects.create(username=request.POST['login'], first_name=request.POST['nome'])
        user.set_password(request.POST['senha'])
    
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
        user.set_password(request.POST['senha'])
        
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
