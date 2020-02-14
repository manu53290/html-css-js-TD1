function Reseau(Oct_adresse,masque)
{
	var Oct_adresseReseau=[0,0,0,0];
	for (var i=0; i<4; i++)
	{
		Oct_adresseReseau[i]=(Oct_adresse[i]&masque[i]);
	}
	return Oct_adresseReseau;
}
function hostID(masque)
{
	var HOST_ID=[0,0,0,0];
	for (var i=0; i<4; i++)
	{
		HOST_ID[i]=255-masque[i];
	}
	return HOST_ID;
}
function netID(hostID)
{
	var NET_ID=[255,255,255,255];
	for (var i=0; i<4; i++)
	{
		NET_ID[i]=255-hostID[i];
	}
	return NET_ID;
}

function octets(adresse)
{
	var Oct_adresses = ["","","",""];
	var point = 0;
	for (var i=0; i<adresse.length; i++)
	{
		if (adresse[i] == '.' ){
			point++;
			continue;
		}
		switch (point)
		{
			case 0: Oct_adresses[0]+=adresse[i]; break;
			case 1: Oct_adresses[1]+=adresse[i]; break;
			case 2: Oct_adresses[2]+=adresse[i]; break;
			case 3: Oct_adresses[3]+=adresse[i]; break;
		}
	}	
	return (Oct_adresses);
}
function Masque(masque)
{
	var masqueFinal=["","","",""];
	var point = 0;
	for (var i=0; i<masque.length; i++)
	{
		if (masque[i] == '.' ){
			point++;
			continue;
		}
		switch (point)
		{
			case 0: masqueFinal[0]+=masque[i]; break;
			case 1: masqueFinal[1]+=masque[i]; break;
			case 2: masqueFinal[2]+=masque[i]; break;
			case 3: masqueFinal[3]+=masque[i]; break;
		}
	}	
	return masqueFinal;
}
function nb_adresse(masque)
{
	var bits=0;
	for (var i=0; i<4; i++)
	{                                     
		if (masque[i]!=255)
		{
			var octet=255-masque[i];
			if (octet>=128)
			{
				bits++;
				octet-=128;
			}
			 if (octet>=64)
			{
				bits++;
				octet-=64;
			}
			 if (octet>=32)
			{
				bits++;
				octet-=32;
			}
			 if (octet>=16)
			{
				bits++;
				octet-=16;
			}
			 if (octet>=8)
			{
				bits++;
				octet-=8;
			}
			 if (octet>=4)
			{
				bits++;
				octet-=4;
			}
			 if (octet>=2)
			{
				bits++;
				octet-=2;
			}
			 if (octet>=1)
			{
				bits++;
				octet-=1;
			}
		}
	}
	var nb=1;
	for (var i=0; i<bits; i++) {nb*=2}
	return nb;
}

function broadcast(host,reseau)
{
	var broadcast=[0,0,0,0];
	for (var i=0; i<4; i++)
	{
		broadcast[i]=host[i]+reseau[i];
	}
	return broadcast;
}


function recup()
{
	var adresse1= document.getElementById("ip1Input").value;
	var Oct_adresse1=octets(adresse1);
	var adresse2 = document.getElementById("ip2Input").value;
	var masqueIP=document.getElementById("MasqueInput").value;
	var masque=Masque(masqueIP);
	var Oct_adresse2=octets(adresse2);
	var Oct_adresseReseau1=Reseau(Oct_adresse1,masque);
	var host_ID=hostID(masque);
	var net_ID=netID(host_ID);
	var Nb_adresses=nb_adresse(masque);
	var adresse_broadcast=broadcast(host_ID,Oct_adresseReseau1);
	document.getElementById("IP1").innerHTML="Adresse 1 : " + Oct_adresse1;
	document.getElementById("IP2").innerHTML="Adresse 2 : "+Oct_adresse2;
	document.getElementById("IPreseau").innerHTML="Adresse reseau :"+Oct_adresseReseau1;
	document.getElementById("netID").innerHTML="NET ID : " + net_ID;
	document.getElementById("hostID").innerHTML="HOST ID : "+host_ID;
	document.getElementById("première_adresse").innerHTML="Première adresse : "+Oct_adresseReseau1[0]+","+Oct_adresseReseau1[1]+","+Oct_adresseReseau1[2]+","+(Oct_adresseReseau1[3]+1);
	document.getElementById("Nb_adresse").innerHTML="Nombres d'adresses disponibles : "+Nb_adresses;
	document.getElementById("broadcast_adresse").innerHTML="Adresse de diffusion : "+adresse_broadcast;
	document.getElementById("dernière_adresse").innerHTML="Dernière adresse : "+adresse_broadcast[0]+","+adresse_broadcast[1]+","+adresse_broadcast[2]+","+(adresse_broadcast[3]-1);
}



