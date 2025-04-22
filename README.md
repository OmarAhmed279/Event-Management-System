
# Event Management System 🎟️  
![Java](https://img.shields.io/badge/Java-17-%23ED8B00?logo=java)  
![JavaFX](https://img.shields.io/badge/JavaFX-19-%230081CB?logo=openjdk)  
[![License: MIT](https://img.shields.io/badge/License-MIT-blue.svg)](https://opensource.org/licenses/MIT)  

A desktop application for organizing events, managing room reservations, and selling tickets. Built for **Introduction to Object-Oriented Programming** at **Ain Shams University**.  

## ✨ Key Features  
- **Role-Based Access**:  
  - **Admins**: Manage users, suspend accounts, and oversee events/rooms.  
  - **Organizers**: Create events, reserve rooms, and track attendees.  
  - **Attendees**: Browse events, purchase tickets, and manage profiles.  
- **Room Reservation**: Check room availability and reserve spaces for events.  
- **Wallet System**: Add/deduct balance for ticket purchases.  
- **Local Database**: Stores all data (users, events, rooms) using a centralized `Database` class.  

## 🚨 Limitations  
- **Offline-Only**: No network/server connectivity—data stays on the local device.  
- **No Real-Time Updates**: Manual refresh required for changes.  
- **No Payment Gateways**: Financial transactions use an internal wallet (no Stripe/PayPal).  

## 🛠️ Tech Stack  
**Core**:  
![Java](https://img.shields.io/badge/Java-17-%23ED8B00?logo=java)  
![JavaFX](https://img.shields.io/badge/JavaFX-19-%230081CB?logo=openjdk)  

**Tools**:  
![Git](https://img.shields.io/badge/Git-%23F05032?logo=git&logoColor=white)  
![IntelliJ](https://img.shields.io/badge/IntelliJ_IDEA-000000?logo=intellij-idea)  

## 🚀 Getting Started  

### Prerequisites  
- Java JDK 17+  
- JavaFX SDK 19+  

### Installation  
1. Clone the repository:  
   ```bash  
   git clone https://github.com/OmarAhmed279/event-management-system.git

2.Import the project into your IDE (e.g., IntelliJ, Eclipse) with JavaFX configured.

3.Run the Main.java file to start the application.

👥 Team
Group Members:

Youssef Mohamed Wafaey (24P0177)

Omar Sherif Mohamed (24P0019)

Omar Ahmed Hussien (24P0081)

Abdallah Ahmed Gomaa (24P0140)

Youssef Shehta Abdelfattah (24P0191)

Seif Shehta Abdelfattah (24P0190)

All members contributed equally to frontend (JavaFX GUI), backend (Java logic), and testing.

📜 License
Distributed under the MIT License. See LICENSE for details.

🔜 Future Improvements
Add QR code check-in for attendees.

Enable PDF/CSV exports for event summaries.

Integrate email notifications for ticket confirmations.
