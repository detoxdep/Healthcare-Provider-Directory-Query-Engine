==============================================================================
FLORIDA-BASED PRIMARY CARE PHYSICIAN & SPECIALIST SEARCH TOOL
PROJECT README (SUBMISSION FORMAT: README.txt)
Course: CEN 4010 - Software Engineering (Spring 2026)
University of North Florida — School of Computing
==============================================================================
================================================================================

PROJECT OVERVIEW
================================================================================
The Doctor Finder application is a desktop solution designed to streamline the
process of locating local medical practitioners and specialists across Florida.
By interfacing directly with standardized local database files, the tool filters
a tailored registry of providers to match user-defined healthcare criteria.

The application computes distance vectors dynamically between users and doctor
clinics using geospatial mathematical modeling (Haversine formula), processes complex
multi-attribute conditional search parameters, and enforces data isolation layers
complying with modern privacy and technical specifications.

Core Architecture Highlights:

Local CSV Database Integration: Ingests, tokenizes, and maps large data entries
from 'random_doctors_florida_zipcodes.csv' and 'florida_zipcodes.csv'.

Multi-tiered OO Inheritance Hierarchy: Implements clean domain object separation
(Person -> Doctor / Patient) for optimized memory retention and reusability.

Dual Interface Design: Offers both an interactive text-based Command Line
Interface (CLI) and a graphic JavaFX user pipeline.

Rigorous Verification Layer: Thoroughly validated utilizing Boundary Value
Analysis (BVA), Equivalence Partitioning (EP), Basis Path branch coverage,
and comprehensive automated unit testing suites.

================================================================================
2. SYSTEM COMPONENT & FILE STRUCTURE
The project workspace contains the following core Java elements and artifacts:

├── florida_zipcodes.csv                # Reference database tracking Lat/Long coordinates
├── random_doctors_florida_zipcodes.csv # Complete regional medical provider data list
│
├── Person.java                         # Base entity for personal detail inheritance
├── Doctor.java                         # Specialized sub-class modeling physician attributes
├── Patient.java                        # Specialized sub-class holding patient profiles
│
├── Search.java                         # Low-level file parser and data ingestion module
├── Distance.java                       # Geospatial coordinate translator and distance engine
├── Filter.java                         # Multi-criteria logical conditional evaluation module
│
├── Main.java                           # Command-line menu interface runner
├── GUI.java                            # Full JavaFX desktop frontend application interface
│
└── README.txt                          # Operation and implementation blueprint (this file)

================================================================================
3. COMPONENT FUNCTIONALITIES
Person.java
The root object class containing baseline attributes common to all human actors
within the system ecosystem (First Name, Middle Initial, Last Name, Age, Sex,
and Primary Physical Address) complete with standard mutator and accessor logic.

Doctor.java
Extends the Person base class. Super-charges the entity with professional
attributes including National Provider Identifier (NPI), enumeration timestamp,
spoken language lists, multi-specialty registries, secondary office locations,
accepted insurance companies, new patient availability toggles, portal URLs,
and consumer review averages.

Patient.java
Extends the Person base class. Encapsulates client-specific elements, tracking
their chosen insurance provider along with explicit profile access tokens
(passwords earmarked for cryptographic security upgrades in subsequent iterations).

Search.java
Operates as the application's Data Ingestion Layer. Leverages standard Java NIO
BufferedReaders to stream lines from the local dataset. Applies sophisticated regular
expression patterns (line.split(",(?=(?:[^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*$)\"))
to safely tokenize comma-separated variables while keeping internal quotes within text
blocks unharmed, returning uniform ArrayList structures.

Distance.java
The geospatial intelligence calculation component. Accesses regional data models
to convert standard 5-digit ZIP codes into fractional coordinates (latitude/longitude).
Applies the Haversine formula to approximate terrestrial curves and outputs real-world
point-to-point intervals measured precisely in miles.

Filter.java
Executes logic matching algorithms over the registry. Validates candidate
Doctor objects against complex, concurrent target criteria such as maximum distance
radii, specific insurance network matching, specialized medical fields, minimal
review tier parameters, and ongoing patient onboarding availability.

Main.java
Provides an alternate terminal user interface layout. Runs a recursive state-machine
loop that lets operators manually declare insurance parameters, update current spatial
ZIP locations, and drill down through independent search parameters through sequential
numeric choice menus.

GUI.java
The main visual engine. Constructed using JavaFX component blocks to build a clean
user journey. Features robust form protection layers that dynamically flash localized
alert modals if necessary data fields (such as target ZIP or Insurance Provider)
remain undefined during search execution.

================================================================================
4. VERIFICATION AND TEST CASE ARCHITECTURE
To assure architectural resilience, the software was evaluated using diverse black-box
and white-box design criteria. The testing matrix targets component operations as follows:

[TC-01] ENVIRONMENT SETUP & RUNTIME ENGINE (GUI / Main)

Purpose: Verify successful frame generation, JavaFX thread initialization,
and error-free local context deployment.

Inputs: Standard system launch hook.

Expected Outcome: Render window populates instantly with home layouts, active
text fields, and populated choice dropdown options visible.

[TC-02] DATA INGESTION AND STRING PARSING (Search)

Purpose: Assert correct comma splitting across fields containing multi-value
blocks protected by embedded quotes (e.g., lists of insurance options).

Inputs: Target entry containing structural data block: "Aetna;BlueCross;Cigna"

Expected Outcome: Parser maps individual string array fields accurately without
truncating tokens or breaking on internal semicolons.

[TC-03] GEOSPATIAL GEODETIC TRANSLATION (Distance)

Purpose: Validate boundary condition checking and Haversine math consistency.

Inputs: Patient ZIP: "32092", Doctor ZIP: "32084".

Expected Outcome: Coordinates look up properly and return real-world distance values in
under 2.0 seconds. Malformed or absent inputs return -1 smoothly without raising errors.

[TC-04] BOUNDARY AND EQUIVALENCE FILTERING (Filter)

Purpose: Enforce exact conditional parameters across complex search sets.

Inputs: Doctor Profile Review score 4.19, Target Criteria Min Review 4.2.

Expected Outcome: Filter returns false (Excludes the provider from output). Assert
100% path coverage for every attribute branch option.

[TC-05] MODEL INHERITANCE INVARIANT CHECKS (Person / Doctor / Patient)

Purpose: Confirm structural integrity of child entities when cast as root types.

Inputs: Multi-class initialization with varying parameters.

Expected Outcome: Verify zero data cross-contamination or parameter drops between
underlying super-constructors and downstream children during object creation.

================================================================================
5. PIPELINE INSTRUCTIONS & EXECUTION
Step 1: System Dependencies Configuration
Ensure you have the Java Development Kit (JDK 17 or higher) installed. This project
uses JavaFX libraries for its graphical components. Verify your local workspace maps
libraries correctly:
$ java --version

Step 2: Database File Placement
Confirm that the companion dataset dependencies reside within the root folder
of your executable working workspace directory:
./florida_zipcodes.csv
./random_doctors_florida_zipcodes.csv

Step 3: Compiling the Application
Compile all project modules via the terminal interface:
$ javac *.java

Step 4: Launching the Graphical User Interface (JavaFX Mode)
To launch the primary interactive desktop application, run the GUI module:
$ java GUI

Step 5: Launching the Text Terminal Interface (CLI Mode)
Alternatively, to run the lightweight command-line menu interface, run the
Main compilation entrypoint:
$ java Main

================================================================================
6. PERFORMANCE & SPECIFICATION INVARIANTS
Performance Metric: Search execution, multi-variable filtering, and distance
mapping operations are optimized to populate matching entries in under 2 seconds.

Privacy Constraint (HIPAA): Local fields are isolated within strict access encapsulation
layers, blocking invalid cross-profile reads or modification across system records.
=============================================================================
