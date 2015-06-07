select * from booking;
select * from user;
select * from system_role;
select * from contact;
select * from user_role;
select * from application_properties where type='SERVICES';
select * from application_properties where type='BOOKING_STATUS';
select * from application_properties;
select * from services;
select * from booking_assignment;
select * from vendor_skill;
select * from vendor_booking_route;

SELECT 
    users.row_id,
    email,
    first_name,
    last_name,
    rates,
    props.property_id,
    value
FROM
    system_role roles,
    user_role user_roles,
    user users,
    contact contact,
    vendor_skill skills,
    application_properties props
WHERE
    roles.role_id = user_roles.role_id
        AND user_roles.user_id = users.row_id
        AND users.row_id = skills.vendor_id
        AND users.address_id = contact.row_id
        AND skills.skill_id = props.property_id
        AND props.type = 'SERVICES'
		AND roles.title = 'VENDOR_ROLE'
        AND roles.is_active = '1'
        AND users.is_active = '1'
        AND props.is_active = '1'
		AND user_roles.user_id = 4;         
SELECT 
    users.row_id,
    email
FROM
    system_role roles,
    user_role user_roles,
    user users
WHERE
    roles.role_id = user_roles.role_id
        AND user_roles.user_id = users.row_id
		AND roles.title = 'VENDOR_ROLE'
        AND roles.is_active = '1'
        AND users.is_active = '1'
		AND user_roles.user_id = 4;         