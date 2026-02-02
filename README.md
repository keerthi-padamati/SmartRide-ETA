# Ride-Sharing ETA Prediction

## Overview

This project simulates a **ride-sharing system** like Uber or Lyft.  
The goal is to find the **nearest driver** for a user and calculate how long it will take for the driver to reach them (**ETA – Estimated Time of Arrival**).

It is done using **core Java** and optimized to handle multiple users at the same time.

---

## Problem Statement

In a ride-sharing app:

- Users request rides from different locations.
- Drivers are moving around the city.
- The system must quickly find the **closest driver** for each user and calculate the ETA.

Challenges:

- Multiple users and drivers at the same time.
- City map has many locations and routes.
- Need **real-time results**.
- Avoid calculating the same route multiple times.

Goal:

> Compute ETA for multiple users efficiently using core Java, caching, and multithreading.

---

## How It Works (Simple Explanation)

1. **City Map**  
   - City is represented as locations connected by roads.  
   - Each road has a travel time in minutes.  


2. **Drivers and Users**  
   - Drivers have a current location.  
   - Users have a pickup location.

3. **Compute ETA**  
   - For each user, calculate travel time to all drivers using a **simple path search**.  
   - Store results in a **cache** to avoid repeating the same calculation.

4. **Select Nearest Driver**  
   - Use a **priority queue** to pick the driver with the smallest ETA.

5. **Concurrent Requests**  
   - Multiple users are processed **at the same time** using threads (`ExecutorService`).

---

## Optimizations Done

- **Distance Cache**: Stores previously calculated distances to save time.  
- **Priority Queue**: Quickly selects nearest driver.  
- **Thread Pool**: Processes multiple users at the same time.  
- **Core Java Only**: No extra libraries needed.

---

## Possible Improvements

- Add **driver movement simulation** and update ETA continuously.  
- Use **real map coordinates** instead of simple city nodes.  
- Implement **Dijkstra’s algorithm** for realistic shortest path.  
- Support **different car types or multiple vehicles per driver**.  
- Handle **many users and drivers** efficiently with better load balancing.

---

## Where It Can Be Used

- Ride-sharing apps like Uber, Lyft, Bolt  
- Food or parcel delivery apps  
- Fleet management systems  
- Any system needing **real-time nearest resource selection**

---

## Example Output

Nearest driver to User1 is Driver3 with ETA 1 mins
Nearest driver to User2 is Driver3 with ETA 0 mins



---


## Future Improvements

1. **Dynamic Users**  
   - Allow users to enter ride requests in real-time.  
   - Requests could come from a **command-line interface, GUI, or web app**.

2. **Dynamic Driver Locations**  
   - Currently, drivers are static.  
   - Drivers could move around the city and ETA could update accordingly.

3. **Real-Time ETA Updates**  
   - Update ETA if drivers move or traffic conditions change, similar to real ride-sharing apps.

4. **Scalability for Any Number of Drivers**  
   - The program works for **any number of drivers**.  
   - Users can **add, remove, or modify drivers** in the code and verify the program still works correctly.

5. **Queue-Based Requests and Thread Management**  
   - Multiple simultaneous requests can be handled efficiently with a **request queue and threads**.

6. **Better ETA Calculation**  
   - Use **real map data, traffic, and shortest path algorithms** like Dijkstra for more realistic ETAs.

7. **Vehicle Type & Availability Management**  
   - Support different vehicle types (cars, bikes, vans).  
   - Track driver availability to assign rides only to free drivers.

**User-Friendly Note:**

> “You can test this program with **any number of drivers**. Simply modify the drivers list in the code with your own IDs and locations. The program will automatically calculate the nearest driver and ETA for any user.”

---
