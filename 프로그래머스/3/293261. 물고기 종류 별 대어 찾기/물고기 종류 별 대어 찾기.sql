SELECT fish.ID, name.FISH_NAME, fish.LENGTH
FROM FISH_INFO fish JOIN FISH_NAME_INFO name ON fish.FISH_TYPE = name.FISH_TYPE
WHERE fish.LENGTH = (
    SELECT MAX(len.LENGTH)
    FROM FISH_INFO len
    WHERE len.FISH_TYPE = fish.FISH_TYPE
)
ORDER BY ID