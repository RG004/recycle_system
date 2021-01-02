/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2021/1/2 18:03:16                            */
/*==============================================================*/


drop table if exists Address;

drop table if exists Admin;

drop table if exists Collector;

drop table if exists Donation;

drop table if exists Evaluation;

drop table if exists Help_The_Poor;

drop table if exists Item;

drop table if exists Item_Type;

drop table if exists Recycle_Order_Detail;

drop table if exists Recycle_Orders;

drop table if exists Recycle_Site;

drop table if exists User;

/*==============================================================*/
/* Table: Address                                               */
/*==============================================================*/
create table Address
(
   Address_ID           int not null auto_increment,
   User_ID              int,
   Address_Details      varchar(255),
   Latitude             double,
   Longitude            double,
   primary key (Address_ID)
);

/*==============================================================*/
/* Table: Admin                                                 */
/*==============================================================*/
create table Admin
(
   Admin_ID             int not null auto_increment,
   User_Name            varchar(20),
   Password             varchar(20),
   primary key (Admin_ID),
   unique key AK_Key_2 (User_Name)
);

/*==============================================================*/
/* Table: Collector                                             */
/*==============================================================*/
create table Collector
(
   Collector_Person_ID  int not null auto_increment,
   Site_ID              int,
   Collector_Name       varchar(20),
   User_Name            varchar(20),
   Password             varchar(20),
   Phone                varchar(11),
   IDcard_Number        varchar(20),
   Photo                varchar(255),
   User_Status          varchar(10),
   primary key (Collector_Person_ID),
   unique key AK_Key_2 (User_Name)
);

/*==============================================================*/
/* Table: Donation                                              */
/*==============================================================*/
create table Donation
(
   Donate_ID            int not null auto_increment,
   Help_ID              int,
   User_ID              int,
   Collector_Person_ID  int,
   Evaluation_ID        int,
   Scheduled_Time       datetime,
   Finished_Time        datetime,
   Donate_Detail        varchar(255),
   primary key (Donate_ID)
);

/*==============================================================*/
/* Table: Evaluation                                            */
/*==============================================================*/
create table Evaluation
(
   Evaluation_ID        int not null auto_increment,
   Evaluation_Details   varchar(255),
   Evaluation_Score     int,
   primary key (Evaluation_ID)
);

/*==============================================================*/
/* Table: Help_The_Poor                                         */
/*==============================================================*/
create table Help_The_Poor
(
   Help_ID              int not null,
   Help_Name            varchar(20),
   Longitude            double,
   Latitude             double,
   Help_Detail          varchar(255),
   primary key (Help_ID)
);

/*==============================================================*/
/* Table: Item                                                  */
/*==============================================================*/
create table Item
(
   Item_ID              int not null auto_increment,
   Item_Type_ID         int,
   Item_Name            varchar(20),
   Item_Price           double,
   Item_Pic             varchar(255),
   primary key (Item_ID)
);

/*==============================================================*/
/* Table: Item_Type                                             */
/*==============================================================*/
create table Item_Type
(
   Item_Type_ID         int not null auto_increment,
   Item_Type_Name       varchar(20),
   primary key (Item_Type_ID)
);

/*==============================================================*/
/* Table: Recycle_Order_Detail                                  */
/*==============================================================*/
create table Recycle_Order_Detail
(
   Recycle_Order_Detail_ID int not null auto_increment,
   Item_ID              int not null,
   Recycle_Order_ID     int not null,
   Quantity             double,
   primary key (Recycle_Order_Detail_ID)
);

/*==============================================================*/
/* Table: Recycle_Orders                                        */
/*==============================================================*/
create table Recycle_Orders
(
   Recycle_Order_ID     int not null auto_increment,
   Address_ID           int,
   Collector_Person_ID  int,
   Evaluation_ID        int,
   User_ID              int,
   Scheduled_Time       datetime,
   Finished_Time        datetime,
   Total_Amount         double,
   primary key (Recycle_Order_ID)
);

/*==============================================================*/
/* Table: Recycle_Site                                          */
/*==============================================================*/
create table Recycle_Site
(
   Site_ID              int not null auto_increment,
   Site_Name            varchar(255),
   Latitude             double,
   Longitude            double,
   primary key (Site_ID)
);

/*==============================================================*/
/* Table: User                                                  */
/*==============================================================*/
create table User
(
   User_ID              int not null auto_increment,
   User_Name            varchar(20) not null,
   User_RealName        varchar(20),
   Password             varchar(20),
   Phone                varchar(11),
   primary key (User_ID)
);

alter table Address add constraint FK_Reference_15 foreign key (User_ID)
      references User (User_ID) on delete restrict on update restrict;

alter table Collector add constraint FK_Reference_4 foreign key (Site_ID)
      references Recycle_Site (Site_ID) on delete restrict on update restrict;

alter table Donation add constraint FK_Reference_11 foreign key (Collector_Person_ID)
      references Collector (Collector_Person_ID) on delete restrict on update restrict;

alter table Donation add constraint FK_Reference_12 foreign key (Evaluation_ID)
      references Evaluation (Evaluation_ID) on delete restrict on update restrict;

alter table Donation add constraint FK_Reference_16 foreign key (Help_ID)
      references Help_The_Poor (Help_ID) on delete restrict on update restrict;

alter table Donation add constraint FK_Reference_9 foreign key (User_ID)
      references User (User_ID) on delete restrict on update restrict;

alter table Item add constraint FK_Reference_8 foreign key (Item_Type_ID)
      references Item_Type (Item_Type_ID) on delete restrict on update restrict;

alter table Recycle_Order_Detail add constraint FK_Reference_6 foreign key (Recycle_Order_ID)
      references Recycle_Orders (Recycle_Order_ID) on delete restrict on update restrict;

alter table Recycle_Order_Detail add constraint FK_Reference_7 foreign key (Item_ID)
      references Item (Item_ID) on delete restrict on update restrict;

alter table Recycle_Orders add constraint FK_Reference_1 foreign key (Address_ID)
      references Address (Address_ID) on delete restrict on update restrict;

alter table Recycle_Orders add constraint FK_Reference_2 foreign key (Collector_Person_ID)
      references Collector (Collector_Person_ID) on delete restrict on update restrict;

alter table Recycle_Orders add constraint FK_Reference_3 foreign key (Evaluation_ID)
      references Evaluation (Evaluation_ID) on delete restrict on update restrict;

alter table Recycle_Orders add constraint FK_Reference_5 foreign key (User_ID)
      references User (User_ID) on delete restrict on update restrict;

