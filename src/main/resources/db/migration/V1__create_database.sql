create table Perfil(
	id int not null auto_increment primary key,
    nome varchar(100)
);

insert into Perfil (nome) values
("Admin"),
("User");

create table Usuario(
	id int not null auto_increment primary key,
    nome varchar(100),
    email varchar(255),
    login varchar(255) unique,
    senha varchar(255),
    ativo boolean default 1
);

create table Item_Perfil_Usuario(
	id int not null auto_increment primary key,
    id_perfil int not null,
    id_usuario int not null,

    constraint FK_Item_Perfil_Usuario_Perfil foreign key (id_perfil)
		references Perfil(id),
	constraint FK_Item_Perfil_Usuario_Usuario foreign key (id_usuario)
		references Usuario(id)
);

create table Curso(
	id int not null auto_increment primary key,
    nome varchar(100),
    categoria ENUM('BACK','FRONT','BANCO','UX')
);

create table Topico(
	id int not null auto_increment primary key,
    titulo varchar(100),
    mensagem text,
    dt_criacao timestamp default current_timestamp,
    st_topico ENUM('ABERTO','FECHADO'),
    id_usuario int not null,
    id_curso int not null,
    ativo boolean default 1,


    constraint FK_Topico_Curso foreign key (id_curso)
		references Curso(id),
	constraint FK_Topico_Usuario foreign key (id_usuario)
		references Usuario(id)
);

create table Resposta(
	id int not null auto_increment primary key,
    mensagem text,
    dt_criacao timestamp default current_timestamp,
    solucao boolean,
    id_usuario int not null,
    id_topico int not null,
    ativo boolean default 1,

    constraint FK_Resposta_Topico foreign key (id_topico)
		references Topico(id),
	constraint FK_Resposta_Usuario foreign key (id_usuario)
		references Usuario(id)
);