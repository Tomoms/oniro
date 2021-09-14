# SPDX-FileCopyrightText: Huawei Inc.
# SPDX-License-Identifier: Apache-2.0

# shadow uses non-literal format strings to integrate system information
# into the login prompt. This is harmless because harmful format strings
# are checked for (and in the worst case, the strings come from a file
# that is writable by root only).
#
# Removing -Werror=format-nonliteral here allows us to use
# -Werror=format-nonliteral globally in OPTIMIZE_FOR=security mode
# while keeping shadow building.

TARGET_CFLAGS_remove = "-Werror=format-nonliteral"