--liquibase formatted sql
--changeset tamas.ranga@gmail.com:202302062130

create schema transparency;
create schema common;
create schema mavir;
create schema forecast;

create table common.sec_role (id bigint not null, name varchar(255) not null, version bigint, primary key (id));
create table common.sec_user (id bigint not null, pwd varchar(255) not null, username varchar(255) not null, version bigint, primary key (id));
create table common.sec_user_role (id bigint not null, version bigint, role_id bigint not null, user_id bigint not null, primary key (id));
alter table common.sec_role add constraint UK_328v0xhgur113t0ak61ieyp8n unique (name);
alter table common.sec_user add constraint UK_5ctbdrlf3eismye20vsdtk8w8 unique (username);
alter table common.sec_user_role add constraint UKosjjrflasbhpcxtwsb4oo01ec unique (role_id, user_id);
create sequence mavir.seq_mavir start with 1000 increment by 1;
create table mavir.document (dtype varchar(31) not null, id bigint not null, revision bigint, time_stamp datetimeoffset(6) not null, igcccontrolled_down numeric(38,2), igcccontrolled_up numeric(38,2), rate_of_automatic_controlled_down numeric(38,2), rate_of_automatic_controlled_up numeric(38,2), rate_of_domestic_controlled_down numeric(38,2), rate_of_domestic_controlled_up numeric(38,2), rate_of_manual_controlled_down numeric(38,2), rate_of_manual_controlled_up numeric(38,2), gross_load_fct numeric(38,2), gross_system_load_validated numeric(38,2), net_estimated_load numeric(38,2), net_load numeric(38,2), net_system_load15m numeric(38,2), net_system_load15minutes numeric(38,2), planned_gross_system_load numeric(38,2), planned_net_system_gen numeric(38,2), planned_net_system_load numeric(38,2), realised_gross_system_load numeric(38,2), current_est_windppgen numeric(38,2), da_est_windppgen numeric(38,2), id_est_windppgen numeric(38,2), solar_net_op_measure numeric(38,2), solar_trade_net_settl_measure numeric(38,2), wind_onshore_gross_op_measure numeric(38,2), wind_onshore_net_op_measure numeric(38,2), wind_onshore_net_settl_measure numeric(38,2), wind_realised_netppgen numeric(38,2), primary key (id));
create sequence transparency.seq_action_status start with 1000 increment by 1;
create sequence transparency.seq_areaid_string start with 1000 increment by 1;
create sequence transparency.seq_esmp_active_power start with 1000 increment by 1;
create sequence transparency.seq_esmp_datetime_interval start with 1000 increment by 1;
create sequence transparency.seq_esmp_voltage start with 1000 increment by 1;
create sequence transparency.seq_financial_price start with 1000 increment by 1;
create sequence transparency.seq_market_document start with 1000 increment by 1;
create sequence transparency.seq_mkt_generating_unit start with 1000 increment by 1;
create sequence transparency.seq_mkt_psr_type start with 1000 increment by 1;
create sequence transparency.seq_outage_asset_registered_resource start with 1000 increment by 1;
create sequence transparency.seq_partyid_string start with 1000 increment by 1;
create sequence transparency.seq_point start with 1000 increment by 1;
create sequence transparency.seq_reason start with 1000 increment by 1;
create sequence transparency.seq_reasourceid_string start with 1000 increment by 1;
create sequence transparency.seq_series_period start with 1000 increment by 1;
create sequence transparency.seq_time_series start with 1000 increment by 1;
create table transparency.action_status (id bigint not null, revision bigint, actionstatus_value varchar(255) not null, primary key (id));
create table transparency.areaid_string (id bigint not null, coding_scheme varchar(255) not null, revision bigint, areaidstring_value varchar(255), primary key (id));
create table transparency.esmp_active_power (id bigint not null, revision bigint, unit varchar(255) not null, power_value float(24), primary key (id));
create table transparency.esmp_datetime_interval (id bigint not null, end_at datetimeoffset(6) not null, revision bigint, start_at datetimeoffset(6) not null, primary key (id));
create table transparency.esmp_voltage (id bigint not null, revision bigint, unit varchar(255) not null, voltage_value float(24), primary key (id));
create table transparency.financial_price (id bigint not null, amount numeric(38,2) not null, direction varchar(255) not null, revision bigint, financial_price_id bigint, primary key (id));
create table transparency.market_document (dtype varchar(31) not null, id bigint not null, mrid varchar(255) not null, created_date_time datetimeoffset(6) not null, receiver_market_participant_market_role_type varchar(255) not null, revision bigint, sender_market_participant_market_role_type varchar(255) not null, revision_number varchar(255), type varchar(255), process_type varchar(255), received_market_document_created_date_time datetimeoffset(6), received_market_documentmrid varchar(255), received_market_document_revision_number varchar(255), received_market_document_title varchar(255), received_market_document_type varchar(255), allocation_decision_date_and_or_time_date_time datetimeoffset(6), receiver_market_participantmrid_id bigint, sender_market_participantmrid_id bigint, doc_status_id bigint, period_time_interval_id bigint, control_area_domainmrid_id bigint, primary key (id));
create table transparency.measuremementpointid_string (id bigint not null, coding_scheme varchar(255) not null, revision bigint, measurementpoint_value varchar(255), primary key (id));
create table transparency.mkt_generating_unit (id bigint not null, generating_unit_location_name varchar(255) not null, generating_unitpsrtype_psr_type varchar(255) not null, name varchar(255) not null, revision bigint, mrid_id bigint not null, nominalp_id bigint not null, generating_unit_power_system_resources_id bigint, primary key (id));
create table transparency.mkt_psr_type (id bigint not null, psr_type varchar(255) not null, revision bigint, nominalippower_system_resources_nominalp_id bigint, production_power_system_resources_high_voltage_limit_id bigint, primary key (id));
create table transparency.outage_asset_registered_resource (id bigint not null, location_name varchar(255), name varchar(255), psr_type_psr_type varchar(255), revision bigint, mrid_id bigint not null, asset_registered_resource_id bigint, primary key (id));
create table transparency.partyid_string (id bigint not null, coding_scheme varchar(255) not null, revision bigint, partyidstring_value varchar(255), primary key (id));
create table transparency.point (dtype varchar(31) not null, id bigint not null, position int not null, quantity numeric(38,2) not null, revision bigint, activation_price_amount numeric(38,2), imbalance_price_amount numeric(38,2), imbalance_price_category varchar(255), max_price_amount numeric(38,2), min_price_amount numeric(38,2), procurement_price_amount numeric(38,2), secondary_quantity numeric(38,2), flow_direction_direction varchar(255), unavailable_quantity_quantity numeric(38,2), point_id bigint, primary key (id));
create table transparency.reason (id bigint not null, code varchar(255) not null, revision bigint, text varchar(255), reason_id bigint, primary key (id));
create table transparency.resourceid_string (id bigint not null, coding_scheme varchar(255) not null, revision bigint, resourceidstring_value varchar(255), primary key (id));
create table transparency.series_period (dtype varchar(31) not null, id bigint not null, revision bigint, resolution numeric(21,0), time_interval_id bigint not null, wind_power_feedin_period_id bigint, available_period_id bigint, in_error_period_id bigint, period_id bigint, primary key (id));
create table transparency.time_series (dtype varchar(31) not null, id bigint not null, mrid varchar(255) not null, revision bigint, business_type varchar(255), currency_unit_name varchar(255), curve_type varchar(255), quantity_measure_unit_name varchar(255), version varchar(255), cancelledts varchar(255), flow_direction_direction varchar(255), mktpsrtype_psr_type varchar(255), price_measure_unit_name varchar(255), type_market_agreement_type varchar(255), original_market_product_market_product_type varchar(255), standard_market_product_market_product_type varchar(255), object_aggregation varchar(255), registered_resource_name varchar(255), end_date_and_or_time_date datetimeoffset(6), end_date_and_or_time_time datetimeoffset(6), production_registered_resource_location_name varchar(255), production_registered_resource_name varchar(255), prod_reg_resource_psrtype_name varchar(255), production_registered_resourcepsrtype_psr_type varchar(255), start_date_and_or_time_date datetimeoffset(6), start_date_and_or_time_time datetimeoffset(6), in_domainmrid_id bigint, out_domainmrid_id bigint, acquiring_domainmrid_id bigint, connecting_domainmrid_id bigint, in_bidding_zone_domainmrid_id bigint, mktpsrtype_id bigint, out_bidding_zone_domainmrid_id bigint, registered_resourcemrid_id bigint, bidding_zone_domainmrid_id bigint, production_registered_resourcemrid_id bigint, prod_reg_resource_psrtype_resource_id bigint, prod_reg_resource_psrtype_nominal_id bigint, rejected_time_series_id bigint, time_series_id bigint, primary key (id));
alter table transparency.mkt_generating_unit add constraint UK_at6d6a8hjnh652n9xecayb47a unique (mrid_id);
alter table transparency.mkt_generating_unit add constraint UK_kghjm9hr8v9dh17f0ncb9xofi unique (nominalp_id);
alter table transparency.outage_asset_registered_resource add constraint UK_tqje5m9im6dvb97fbkacgbpu9 unique (mrid_id);
alter table transparency.series_period add constraint UK_79gvojstyhskiqf44r7eqb5nl unique (time_interval_id);
create sequence seq_role start with 1000 increment by 1;
create sequence seq_seq_measurementpointid_string start with 1 increment by 50;
create sequence seq_user start with 1000 increment by 1;
create sequence seq_user_role start with 1000 increment by 1;
alter table common.sec_user_role add constraint FKfowkd8vw5qarh8b8y9noaf4et foreign key (role_id) references common.sec_role;
alter table common.sec_user_role add constraint FK835bbyiy6majrolcov7bp0yo0 foreign key (user_id) references common.sec_user;
alter table transparency.financial_price add constraint FK8bu8l86yyc564k8o6bgrhqt57 foreign key (financial_price_id) references transparency.point;
alter table transparency.market_document add constraint FKk368f05jgylup06l6jh5atv3d foreign key (receiver_market_participantmrid_id) references transparency.partyid_string;
alter table transparency.market_document add constraint FK9ad9hhxwmsq337b1km9aa55sd foreign key (sender_market_participantmrid_id) references transparency.partyid_string;
alter table transparency.market_document add constraint FKt9xp63fi8f69xt9g22c7fun6v foreign key (doc_status_id) references transparency.action_status;
alter table transparency.market_document add constraint FKdfji12o4gxyof2b6ot60msy9r foreign key (period_time_interval_id) references transparency.esmp_datetime_interval;
alter table transparency.market_document add constraint FK4x26lj20x46ccvxppk0ej4w3i foreign key (control_area_domainmrid_id) references transparency.areaid_string;
alter table transparency.mkt_generating_unit add constraint FKdacq152f1mlrnwyk5t7pkxd29 foreign key (mrid_id) references transparency.resourceid_string;
alter table transparency.mkt_generating_unit add constraint FK755512qe47xpwneoyiym9ubec foreign key (nominalp_id) references transparency.esmp_active_power;
alter table transparency.mkt_generating_unit add constraint FKtdrw561j8lbhc8aiurv4lcxkb foreign key (generating_unit_power_system_resources_id) references transparency.mkt_psr_type;
alter table transparency.mkt_psr_type add constraint FKov3jp03d98ie47i3dliwyuo8u foreign key (nominalippower_system_resources_nominalp_id) references transparency.esmp_active_power;
alter table transparency.mkt_psr_type add constraint FKf1pyxgbdwsxc5lg9smtb8q7rm foreign key (production_power_system_resources_high_voltage_limit_id) references transparency.esmp_voltage;
alter table transparency.outage_asset_registered_resource add constraint FKppxm5r6suyl8ivrmj8hm1wqk9 foreign key (mrid_id) references transparency.resourceid_string;
alter table transparency.outage_asset_registered_resource add constraint FKb8w4bt6sjdttknef4e7hcf3bt foreign key (asset_registered_resource_id) references transparency.time_series;
alter table transparency.point add constraint FKqj1ntc8l8boml50fw1ve3tfyr foreign key (point_id) references transparency.series_period;
alter table transparency.reason add constraint FK12vjpvd1ofrdpjmhogypb81hr foreign key (reason_id) references transparency.market_document;
alter table transparency.series_period add constraint FK1gnt47wb02lyp627fusqkdg7d foreign key (time_interval_id) references transparency.esmp_datetime_interval;
alter table transparency.series_period add constraint FKbwwl0hgckbdiboya80hpkrisg foreign key (wind_power_feedin_period_id) references transparency.time_series;
alter table transparency.series_period add constraint FKmyko18924t0vljv13np0ap2pv foreign key (available_period_id) references transparency.time_series;
alter table transparency.series_period add constraint FKhiedflyadlaf7dt61hg3a224j foreign key (in_error_period_id) references transparency.time_series;
alter table transparency.series_period add constraint FKmfnwqfhie2jipbyyf4xwl15xo foreign key (period_id) references transparency.time_series;
alter table transparency.time_series add constraint FKqxsravvwmkjod5pv17tgpjc69 foreign key (in_domainmrid_id) references transparency.areaid_string;
alter table transparency.time_series add constraint FK3jhcfebfs2y73sg2chkr0n9lh foreign key (out_domainmrid_id) references transparency.areaid_string;
alter table transparency.time_series add constraint FK83w8h5vy3j85i7k11eaus2ewe foreign key (acquiring_domainmrid_id) references transparency.areaid_string;
alter table transparency.time_series add constraint FK2ue3vh4cv99v7wbwfat5605x2 foreign key (connecting_domainmrid_id) references transparency.areaid_string;
alter table transparency.time_series add constraint FKml8di8dwlv7kioj4yatvl1pqn foreign key (in_bidding_zone_domainmrid_id) references transparency.areaid_string;
alter table transparency.time_series add constraint FKo6w8u9w58cxycujx75otdbmkq foreign key (mktpsrtype_id) references transparency.mkt_psr_type;
alter table transparency.time_series add constraint FK4pc10ue3owud29ko7xwwn3koi foreign key (out_bidding_zone_domainmrid_id) references transparency.areaid_string;
alter table transparency.time_series add constraint FKjuu6so4kd0c39o70xeua8y9ph foreign key (registered_resourcemrid_id) references transparency.resourceid_string;
alter table transparency.time_series add constraint FKk9oa7ievbrrrko2p7och3k4x1 foreign key (bidding_zone_domainmrid_id) references transparency.areaid_string;
alter table transparency.time_series add constraint FKecpidfpgy39dsyju4b2poyu4n foreign key (production_registered_resourcemrid_id) references transparency.resourceid_string;
alter table transparency.time_series add constraint FKjtsbq3ggoasbl8u0wr0nie00y foreign key (prod_reg_resource_psrtype_resource_id) references transparency.resourceid_string;
alter table transparency.time_series add constraint FKjjkaf9lilumqh9dcn3il29txy foreign key (prod_reg_resource_psrtype_nominal_id) references transparency.esmp_active_power;
alter table transparency.time_series add constraint FK1sx4jm4gvtnj95n8nibyymw07 foreign key (rejected_time_series_id) references transparency.market_document;
alter table transparency.time_series add constraint FK3atni6rxtu54c3fh6m57d6qwj foreign key (time_series_id) references transparency.market_document;
create sequence forecast.seq_forecast start with 4000 increment by 1;
create table forecast.inputs (id bigint not null, revision bigint, time_stamp datetimeoffset(6) not null, activation_afrr_igcc_neg numeric(38,2), activation_afrr_igcc_pos numeric(38,2), activation_afrr_local_neg numeric(38,2), activation_afrr_local_pos numeric(38,2), activation_afrr_neg numeric(38,2), activation_afrr_pos numeric(38,2), activation_mfrr_neg numeric(38,2), activation_mfrr_pos numeric(38,2), generation_conventional numeric(38,2), generation_scheduled numeric(38,2), generation_solar numeric(38,2), generation_solar_fc_current numeric(38,2), generation_solar_fc_current_mvr numeric(38,2), generation_solar_fc_da_mvr numeric(38,2), generation_solar_fc_id_mvr numeric(38,2), generation_solar_mvr numeric(38,2), generation_solar_settl_mvr numeric(38,2), generation_water numeric(38,2), generation_wind numeric(38,2), generation_wind_fc_current numeric(38,2), generation_wind_fc_current_mvr numeric(38,2), generation_wind_fc_da_mvr numeric(38,2), generation_wind_fc_id_mvr numeric(38,2), generation_wind_mvr numeric(38,2), imbalance numeric(38,2), imbalance_mvr numeric(38,2), load numeric(38,2), load_forecast numeric(38,2), load_gross_est_mvr numeric(38,2), load_gross_fc_mvr numeric(38,2), load_gross_mvr numeric(38,2), load_net_est_mvr numeric(38,2), load_net_fc_mvr numeric(38,2), load_net_mvr numeric(38,2), cos_time numeric(38,2), sin_time numeric(38,2), primary key (id));
create table forecast.inputsmin (id bigint not null, revision bigint, time_stamp datetimeoffset(6) not null, load_gross_est_mvr_min numeric(38,2), load_gross_mvr_min numeric(38,2), load_net_mvr_min numeric(38,2), primary key (id));


-- Default users
insert into common.sec_role values(1,'ROLE_ADMIN',0);
insert into common.sec_role values(2,'ROLE_USER',0);
insert into common.sec_user values(1,'$2a$10$Xr.QPixIi3wTtCvcS.ODR.bj5pNNdW3Kt1lPN/sOLhW6PxXGdcDgS','admin',0);
insert into common.sec_user values(2,'$2a$10$Xr.QPixIi3wTtCvcS.ODR.bj5pNNdW3Kt1lPN/sOLhW6PxXGdcDgS','user',0);
-- admin/ADMIN
-- admin/USER
insert into common.sec_user_role values(3,0,2,1);
insert into common.sec_user_role values(1,0,1,1);
-- user/USER
insert into common.sec_user_role values(2,0,2,2);

--changeset trks1970@gmail.com:202302141230
create sequence forecast.seq_calculated_forecast start with 4000 increment by 1;
create table forecast.calculated_forecast (id bigint not null, created_timestamp datetimeoffset(6) not null, forecast_timestamp datetimeoffset(6) not null, imbalance_value int not null, revision bigint, primary key (id));
alter table forecast.calculated_forecast add constraint IDX_UNIQUE_CREATED_FORECAST unique (created_timestamp, forecast_timestamp);

--changeset trks1970@gmail.com:202302261630
alter table forecast.inputs add constraint IDX_UNIQUE_INPUTS_TIMESTAMP unique (time_stamp);
alter table forecast.inputsmin add constraint IDX_UNIQUE_INPUTSMIN_TIMESTAMP unique (time_stamp);

--changeset trks1970@gmail.com:202302262130
alter table mavir.document add constraint IDX_UNIQUE_DOCUMENT_TIMESTAMP unique (time_stamp, dtype);
alter table transparency.market_document add endpoint varchar(255) not null default 'outdated';

--changeset trks1970@gmail.com:202302270830
alter table forecast.inputsmin add cos_time numeric(38,2) not null;
alter table forecast.inputsmin add sin_time numeric(38,2) not null;
alter table forecast.inputs alter column cos_time numeric(38,2) not null;
alter table forecast.inputs alter column sin_time numeric(38,2) not null;
