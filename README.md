# Mollo
# Esusu 

## Endpiont to register a new member -> `POST (/groupMembers/)`

## Endpoint to update a registered member's details, this endpoint sets the group you want to belong to and the amount you want to save  -> `PUT (/groupMembers/{groupMemberId})`

## Endpiont to register an admin -> `POST (/groupAdmins)`

## Endpoint to list out all groups available in the database -> `GET (/groupAdmins)`

## Endpoint to get the members registered to a group -> `GET (/groupAdmins//{groupAdminId}/{nameOfGroup})`

## Endpoint to create a group. Note that you must have registered as an admin before you can create a group -> `POST (/esusuGroup/{adminId})`  