# Projet interopérabilité des SI 2019-2020 (groupe 5)

Le groupe est composé des personnes suivantes :

  - Bineta DIAGNE,
  - Li-San SULTAN,
  - Malik MAHAMOUD ABDALLAH,
  - Romain BRUNET,
  - Yoann TOUATI.

# Utilisation

Il faut lancer les services dans cet ordre :

  - eureka-services (port 8761, annuaire où sont regroupés les services),
  - zull (port 8765, passerelle des services permettant le lien entre tous les services),
  - userservices (port 8000, services de connexion),
  - musee-services (port 8001, gestion du musée),
  - paiement-services (port 8003, gestion des paiements via Stripe).

# URI des API

Les URI des API sont détaillées dans le rapport, disponible à cette adresse : https://docs.google.com/document/d/1NMRTJp4hTLPc57NqQVBI5FNupq5I4JaUiCp_rpPGoe8/edit?usp=sharing