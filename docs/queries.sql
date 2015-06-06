select * from booking;
select * from user;
select * from contact;
select * from system_role;
select * from application_properties where type='SERVICES';
select * from application_properties where type='BOOKING_STATUS';
select * from services;
select * from booking_assignment;

select property_id, name, value, rate, zipcode from application_properties a, services b where a.property_id = b.service_id;

select a.row_id, booked_datetime, duration,start_datetime, finish_datetime, client_id, status_desc, status_id, service_id, address,
b.email, b.first_name, b.last_name, c.street,
d.name
from booking a,  user b, contact c, application_properties d
where a.client_id=b.row_id 
and a.service_id = d.property_id
and b.row_id=c.row_id;

SELECT 
    services3_.*, booking0_.*, applicatio1_.*, applicatio2_.*, user4_.*
FROM
    booking booking0_
        INNER JOIN
    application_properties applicatio1_ ON booking0_.service_id = applicatio1_.property_id
        INNER JOIN
    services services3_ ON applicatio1_.property_id = services3_.service_id
        INNER JOIN
    application_properties applicatio2_ ON booking0_.status_id = applicatio2_.property_id
        CROSS JOIN
    user user4_
WHERE
    booking0_.client_id = user4_.row_id
        AND user4_.is_active = '1';