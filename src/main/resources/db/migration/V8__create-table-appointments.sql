create table appointments(

    id bigint not null auto_increment,
    medic_id bigint not null,
    pacient_id bigint not null,
    data datetime not null,

    primary key(id),
    constraint fk_appointments_medic_id foreign key(medic_id) references medics(id),
    constraint fk_appointments_pacient_id foreign key(pacient_id) references pacients(id)

);