# Scope and layout

This public Inner Fence repository contains the `chargeapi` Android library
and a separate demonstration app. When included as a submodule, also follow
the parent project's applicable guidance. Inspect Git status and diff in this
repository before editing, and preserve existing changes. Do not commit unless
requested.

`chargeapi/src/main/java/com/innerfence/chargeapi/ChargeRequest.java` and
`ChargeResponse.java` define the public Intent/Bundle payment contract. Preserve
parameter names, response codes, amount semantics, request codes, and the explicit
`com.innerfence.ccterminal.TerminalActivity` component. The `app/` module shows
the caller flow; changes there do not change the terminal app.

Keep maintenance changes scoped and follow the existing Java/XML conventions.
Use [README.markdown](README.markdown) for integration examples and field details;
check the source when changing the contract.

# Build and verification

Use JDK 17, SDK 35, and build-tools 35.0.1. From this repository's directory:

```sh
./gradlew :chargeapi:assembleDebug :app:assembleDebug
./gradlew :chargeapi:lintDebug :app:lintDebug
```
