**Real Estate Project**

Team: TTD
-   Si Duy Truong
-   Minh Toan Nguyen
-   Dinh Trung Tran

For the final project, we applied the Microservices Architecture, it’s including:
-   Separate our monolithic to microservices.
-   Build the API gateway to manage the domain business.
-   Using configuration service to store all the settings of all services.
-   Separate database following the microservice architecture.
-   Build core to avoid duplicated code between service with service.
-   Using RappitMQ to push message for notification service.
-   Using FeignClient to communicate service with service.

| **Functional Requirements**                                                                                                                                          | **Status** |
|----------------------------------------------------------------------------------------------------------------------------------------------------------------------|------------|
| Users can create accounts using email and password. The system must verify the user's identity and ensure secure authentication.                                     | DONE       |
| Users can create and edit their profiles, including personal information, preferences, and property interests.                                                       | DONE       |
| Sellers and agents can create, edit, and delete property listings, including details like photos, price, and descriptions.                                           | DONE       |
| Users can search for properties using various filters such as location, price range, property type, and amenities.                                                   | DONE       |
| Users can save their favorite properties and searches for easy access in the future.                                                                                 | DONE       |
| System to notify users about new matches, messages, listings, or updates relevant to their preferences.                                                              | DONE       |
| Tools for admins to review, approve, or reject listings, profiles, and reviews to maintain platform standards.                                                       | DONE       |
| Admin can Activate/Deactivate users.                                                                                                                                 | DONE       |
| All delete operations should be a soft deletion.                                                                                                                     | DONE       |
| Log all user activities on the system.                                                                                                                               | DONE       |
| Admin can reset passwords.                                                                                                                                           | DONE       |
| Limit login attempts. If a user tries to login with an invalid password more than 5 times, the system will lock the user and make it unable to login for 15 minutes. | DONE       |
| Secure messaging functionality for users to communicate directly within the platform.                                                                                | DONE       |

