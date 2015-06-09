select * from booking;
select * from booking_event;
select * from booking_feedback;
select * from user;
select * from system_role;
select * from contact;
select * from user_role;
select * from application_properties where type='SERVICES';
select * from application_properties where type='BOOKING_STATUS';
select * from application_properties;
select * from services;
select * from vendor_skill;
select * from vendor_booking_route;

select * from booking_event where booking_id=2 order by row_id desc limit 1;
